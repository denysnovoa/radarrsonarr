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
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class MovieItemAdapterAnko(listener: (MovieViewModel) -> Unit)
    : BaseAdapter<MovieViewModel, MovieItemAdapterAnko.Component>(listener) {

    lateinit var picasso: Picasso

    override val bind: Component.(item: MovieViewModel) -> Unit = {
        title.text = it.title

        picasso.load(it.imagePoster)
                .into(image)
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
                        backgroundColor = R.color.cardview_dark_background
                    }.lparams(width = matchParent, height = dimen(R.dimen.image_row_item))

                    title = textView {
                        padding = dip(8)
                        backgroundResource = R.color.cardview_shadow_start_color
                        setTextAppearanceCompatible(R.style.TextAppearance_AppCompat_Subhead)
                        maxLines = 1
                        ellipsize = TextUtils.TruncateAt.END
                        textSize = dimen(R.dimen.text_size).toFloat()
                    }.lparams(width = matchParent)

                }.lparams(width = matchParent, height = wrapContent)

            }
        }
    }
}
