package com.denysnovoa.nzbmanager.radarr.movie.list.view.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.BaseAdapter
import com.denysnovoa.nzbmanager.common.framework.ui.ViewAnkoComponent
import com.denysnovoa.nzbmanager.common.framework.ui.setTextAppearanceCompatible
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel
import org.jetbrains.anko.*

class MovieItemAdapterAnko(listener: (MovieViewModel) -> Unit)
    : BaseAdapter<MovieViewModel, MovieItemAdapterAnko.Component>(listener) {

    override val bind: Component.(item: MovieViewModel) -> Unit = {
        title.text = it.title
    }

    override fun onCreateComponent(parent: RecyclerView) = Component(parent)

    class Component(override val view: RecyclerView) : ViewAnkoComponent<RecyclerView> {

        lateinit var title: TextView
        lateinit var image: ImageView

        override fun createView(ui: AnkoContext<RecyclerView>) = with(ui) {
            frameLayout {
                verticalLayout {
                    image = imageView {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        backgroundColor = R.color.cardview_light_background
                    }
                    title = textView {
                        padding = R.dimen.padding_16dp
                        setTextAppearanceCompatible(R.style.TextAppearance_AppCompat_Subhead_Inverse)
                        maxLines = 1
                        ellipsize = TextUtils.TruncateAt.END
                    }.lparams(width = matchParent)
                }.lparams(width = matchParent)
            }
        }
    }
}
