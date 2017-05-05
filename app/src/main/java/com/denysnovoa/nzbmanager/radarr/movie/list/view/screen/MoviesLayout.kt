package com.denysnovoa.nzbmanager.radarr.movie.list.view.screen

import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.ActivityAnkoComponent
import com.denysnovoa.nzbmanager.common.framework.ui.custom.RecyclerViewAnko
import com.denysnovoa.nzbmanager.common.framework.ui.custom.recyclerViewAnko
import com.denysnovoa.nzbmanager.common.framework.ui.custom.style
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent


class MoviesLayout : ActivityAnkoComponent<MoviesActivity> {

    lateinit var recycler: RecyclerView
    override lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<MoviesActivity>) = with(ui) {
        coordinatorLayout {
            appBarLayout {
                toolbar = toolbar(R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
                    backgroundResource = R.color.colorPrimary
                }.lparams(width = matchParent) {
                    scrollFlags = SCROLL_FLAG_SNAP or SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS
                }
            }.lparams(width = matchParent)

            recycler = recyclerViewAnko()
                    .apply(RecyclerViewAnko::style)
                    .lparams(matchParent, matchParent) {
                        behavior = AppBarLayout.ScrollingViewBehavior()
                    }


//            recycler = recyclerView {
//                layoutManager = GridLayoutManager(context, 3)
//            }.lparams(matchParent, wrapContent) {
//                behavior = AppBarLayout.ScrollingViewBehavior()
//            }
        }
    }

}