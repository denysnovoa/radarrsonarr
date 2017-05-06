package com.denysnovoa.nzbmanager.radarr.movie.release.view.adapter

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.BaseAdapter
import com.denysnovoa.nzbmanager.common.framework.ui.ViewAnkoComponent
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel
import org.jetbrains.anko.*


class MovieReleaseAdapterAnko(listener: (MovieReleaseViewModel) -> Unit)
    : BaseAdapter<MovieReleaseViewModel, MovieReleaseAdapterAnko.Component>(listener) {

    override val bind: Component.(item: MovieReleaseViewModel) -> Unit = {
        title.text = it.title
        age.text = it.age.toString()
        indexer.text = it.indexer
        size.text = it.size.toString()
        peers.text = " $it.seeders/ $it.leechers "
        rejection.text = "rejection: $it.rejected"
    }

    override fun onCreateComponent(recyclerView: RecyclerView) = Component(recyclerView)

    class Component(override val view: RecyclerView) : ViewAnkoComponent<RecyclerView> {

        lateinit var title: TextView
        lateinit var age: TextView
        lateinit var indexer: TextView
        lateinit var size: TextView
        lateinit var peers: TextView
        lateinit var quality: TextView
        lateinit var rejection: TextView
        lateinit var iconDownload: ImageView

        override fun createView(ui: AnkoContext<RecyclerView>) = with(ui) {
            relativeLayout {
                linearLayout {
                    age = textView {
                        padding = dip(8)
                    }

                    title = textView {
                        padding = dip(8)
                    }
                    indexer = textView {
                        padding = dip(8)
                    }

                }.lparams(matchParent, wrapContent)

                linearLayout {

                    size = textView {
                        padding = dip(8)
                    }
                    peers = textView {
                        padding = dip(8)
                    }
                    quality = textView {
                        padding = dip(8)
                    }
                    rejection = textView {
                        padding = dip(8)
                    }

                    iconDownload = imageView(R.drawable.ic_arrow_downward_black_24dp) {
                        scaleType = ImageView.ScaleType.CENTER
                    }

                }.lparams(matchParent, wrapContent)

            }
        }
    }

}