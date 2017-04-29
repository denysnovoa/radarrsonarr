package com.denysnovoa.nzbmanager.di

import android.content.Context
import com.denysnovoa.nzbmanager.common.framework.BaseApplication
import com.denysnovoa.nzbmanager.di.qualifier.ApplicationQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class ApplicationModule(val app: BaseApplication) {
    @Provides @Singleton
    fun provideApp() = app

    @Provides @ApplicationQualifier
    fun provideApplicationContext(): Context = app
}