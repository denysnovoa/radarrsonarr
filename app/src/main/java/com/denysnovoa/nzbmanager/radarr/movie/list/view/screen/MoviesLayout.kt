package com.denysnovoa.nzbmanager.radarr.movie.list.view.screen

import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.*
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
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
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class MoviesLayout : ActivityAnkoComponent<MoviesActivity> {

    lateinit var recycler: RecyclerView
    lateinit var swipe: SwipeRefreshLayout
    override lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<MoviesActivity>) = with(ui) {
        coordinatorLayout {
            appBarLayout(R.style.AppTheme) {
                toolbar = toolbar(R.style.AppTheme) {
                    setTitleTextColor(R.color.colorTextWhite)
                }.lparams(width = matchParent) {
                    scrollFlags = SCROLL_FLAG_SNAP or SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS
                }
            }.lparams(width = matchParent, height = wrapContent)

            swipe = swipeRefreshLayout {

            }
            recycler = recyclerView {
                layoutManager = GridLayoutManager(context, 3)
                clipToPadding = false
                horizontalPadding = dimen(R.dimen.recycler_spacing)
                verticalPadding = dip(2)
                scrollBarStyle = android.view.View.SCROLLBARS_OUTSIDE_OVERLAY
                addItemDecoration(PaddingItemDecoration(dip(2)))
                itemAnimator = DefaultItemAnimator()
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

}