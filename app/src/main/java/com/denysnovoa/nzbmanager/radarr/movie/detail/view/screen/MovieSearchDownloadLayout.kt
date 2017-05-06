package com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen

import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.*
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.ActivityAnkoComponent
import com.denysnovoa.nzbmanager.common.framework.ui.custom.PaddingItemDecoration
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MovieSearchDownloadLayout : ActivityAnkoComponent<MovieSearchDownloadActivity> {

    lateinit var recycler: RecyclerView
    override lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<MovieSearchDownloadActivity>) = with(ui) {
        coordinatorLayout {
            appBarLayout {
                toolbar = toolbar {
                    elevation = dip(4).toFloat()
                }.lparams(width = matchParent) {
                    scrollFlags = SCROLL_FLAG_SNAP or SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS
                }
            }.lparams(width = matchParent)

            recycler = recyclerView {
                layoutManager = GridLayoutManager(context, 1)
                clipToPadding = false
                scrollBarStyle = android.view.View.SCROLLBARS_OUTSIDE_OVERLAY
                horizontalPadding = dimen(R.dimen.recycler_spacing)
                verticalPadding = dip(2)
                addItemDecoration(PaddingItemDecoration(dip(2)))
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

        }

    }
}