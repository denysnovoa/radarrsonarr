package com.denysnovoa.nzbmanager.common.framework.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.jetbrains.anko.onClick
import kotlin.properties.Delegates

abstract class BaseAdapter<Item, Component : ViewAnkoComponent<RecyclerView>>(val listener: (Item) -> Unit = {})
    : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<Component>>() {

    abstract val bind: Component.(item: Item) -> Unit

    var items: List<Item> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    abstract fun onCreateComponent(recyclerView: RecyclerView): Component

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Component> {
        return BaseViewHolder(onCreateComponent(parent as RecyclerView))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Component>, position: Int) {
        val item = items[position]
        holder.itemView.onClick { listener(item) }
        holder.ui.bind(item)
    }

    class BaseViewHolder<out Component : ViewAnkoComponent<RecyclerView>>(val ui: Component)
        : RecyclerView.ViewHolder(ui.inflate())
}
