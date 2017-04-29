package com.denysnovoa.nzbmanager.common.framework

import android.app.Application
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.ApplicationModule
import com.denysnovoa.nzbmanager.di.DaggerApplicationComponent

class BaseApplication : Application() {

    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    fun initializeDagger() {
        graph = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}