package com.denysnovoa.nzbmanager.common.framework

import android.app.Application
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.ApplicationModule
import com.denysnovoa.nzbmanager.di.DaggerApplicationComponent

class BaseApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}