package com.denysnovoa.nzbmanager.common.framework.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.denysnovoa.nzbmanager.common.framework.BaseApplication
import com.denysnovoa.nzbmanager.di.ApplicationComponent

abstract class BaseActivityAnko<out UI : ActivityAnkoComponent<out AppCompatActivity>> : AppCompatActivity() {

    abstract val ui: UI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies(BaseApplication.graph)

    }

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)
}