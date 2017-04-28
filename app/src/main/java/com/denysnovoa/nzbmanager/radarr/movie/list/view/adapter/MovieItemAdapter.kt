package com.denysnovoa.nzbmanager.radarr.movie.list.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.inflate
import com.denysnovoa.nzbmanager.common.framework.loadUrl
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieItemAdapter(val items: List<MovieViewModel>, val listener: (MovieViewModel) -> Unit) : RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.itemView) {
        val item = items[position]
        with(item) {
            movie_title.text = title
            movie_status.text = status
            movie_downloaded.text = downloaded.toString()
            movie_image.loadUrl(imagePoster)

            setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.movie_item))
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}