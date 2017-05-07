package com.denysnovoa.nzbmanager.radarr.movie.release.view.adapter

import android.graphics.Typeface.DEFAULT_BOLD
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.BaseAdapter
import com.denysnovoa.nzbmanager.common.framework.ui.ViewAnkoComponent
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class MovieReleaseAdapterAnko(listener: (MovieReleaseViewModel) -> Unit)
    : BaseAdapter<MovieReleaseViewModel, MovieReleaseAdapterAnko.Component>(listener) {

    override val bind: Component.(item: MovieReleaseViewModel) -> Unit = {
        title.text = it.title
        age.text = it.age.toString()
        indexer.text = it.indexer
        size.text = it.size.toString()
        peers.text = " ${it.seeders}/ ${it.leechers} "

        if (!it.rejected) {
            iconDownload.visibility = View.GONE
        } else {
            iconDownload.setOnClickListener { listener }
        }
    }

    override fun onCreateComponent(recyclerView: RecyclerView) = Component(recyclerView)

    class Component(override val view: RecyclerView) : ViewAnkoComponent<RecyclerView> {

        lateinit var title: TextView
        lateinit var age: TextView
        lateinit var indexer: TextView
        lateinit var size: TextView
        lateinit var peers: TextView
        lateinit var quality: TextView
        lateinit var iconDownload: ImageView

        override fun createView(ui: AnkoContext<RecyclerView>) = with(ui) {
            verticalLayout {
                lparams(width = matchParent)

                cardView {
                    linearLayout {
                        verticalLayout {
                            linearLayout {
                                padding = dip(8)

                                title = textView {
                                    textSizeDimen = R.dimen.text_primary
                                    typeface = DEFAULT_BOLD
                                }
                            }
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.HORIZONTAL
                                size = textView {
                                    padding = dip(4)
                                    textSizeDimen = R.dimen.text_secondary
                                }
                                age = textView {
                                    padding = dip(4)
                                    textSizeDimen = R.dimen.text_secondary
                                }

                                indexer = textView {
                                    padding = dip(4)
                                    textSizeDimen = R.dimen.text_secondary
                                }
                                peers = textView {
                                    padding = dip(4)
                                    textSizeDimen = R.dimen.text_secondary
                                }
                                quality = textView {
                                    padding = dip(4)
                                    textSizeDimen = R.dimen.text_secondary
                                }

                            }

                        }

                        iconDownload = imageView(R.drawable.ic_arrow_downward_black_24dp) {
                            backgroundColor = R.color.cardview_dark_background
                            scaleType = ImageView.ScaleType.CENTER_CROP
                        }.lparams(width = dimen(R.dimen.icon_download), height = dimen(R.dimen.icon_download)) {
                            padding = dip(8)
                            margin = dip(8)
                        }
                    }
                }
            }
        }

    }

}