package com.denysnovoa.nzbmanager.common.framework.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.denysnovoa.nzbmanager.common.framework.BaseApplication
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import org.jetbrains.anko.setContentView

abstract class BaseActivityAnko<out UI : ActivityAnkoComponent<out AppCompatActivity>> : AppCompatActivity() {

    abstract val ui: UI

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(BaseApplication.graph)

        (ui as ActivityAnkoComponent<AppCompatActivity>).setContentView(this)
        setSupportActionBar(ui.toolbar)
    }

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)
}