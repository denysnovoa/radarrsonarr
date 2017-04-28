package com.denysnovoa.nzbmanager.common.framework

import android.app.Application
import com.denysnovoa.nzbmanager.common.framework.di.ApplicationComponent
import com.denysnovoa.nzbmanager.common.framework.di.ApplicationModule
import com.denysnovoa.nzbmanager.common.framework.di.DaggerApplicationComponent

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