package com.denysnovoa.nzbmanager.common.framework.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.denysnovoa.nzbmanager.common.framework.BaseApplication
import com.denysnovoa.nzbmanager.di.ApplicationComponent

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies(BaseApplication.graph)

    }


    abstract fun injectDependencies(applicationComponent: ApplicationComponent)
}