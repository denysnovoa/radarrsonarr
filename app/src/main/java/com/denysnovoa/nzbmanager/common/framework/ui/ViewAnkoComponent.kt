package com.denysnovoa.nzbmanager.common.framework.ui

import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

interface ViewAnkoComponent<T : ViewGroup> : AnkoComponent<T> {
    val view: T
    fun inflate(): View {
        return createView(AnkoContext.Companion.create(view.context, view))
    }
}