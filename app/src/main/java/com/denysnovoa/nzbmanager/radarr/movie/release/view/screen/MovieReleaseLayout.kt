package com.denysnovoa.nzbmanager.radarr.movie.release.view.screen

import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.R.style.AppTheme
import com.denysnovoa.nzbmanager.common.framework.ui.ActivityAnkoComponent
import com.denysnovoa.nzbmanager.common.framework.ui.custom.PaddingItemDecoration
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MovieReleaseLayout : ActivityAnkoComponent<MovieReleaseActivity> {

    lateinit var recycler: RecyclerView
    override lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<MovieReleaseActivity>) = with(ui) {
        coordinatorLayout {
            appBarLayout {
                toolbar = toolbar(AppTheme) {
                    elevation = dip(4).toFloat()
                    setTitleTextColor(R.color.colorTextWhite)

                    title = context.getString(R.string.title_activity_movie_release)
                }.lparams(width = matchParent) {
                    scrollFlags = SCROLL_FLAG_SNAP or SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS
                }
            }.lparams(width = matchParent)

            recycler = recyclerView {
                layoutManager = LinearLayoutManager(context)
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