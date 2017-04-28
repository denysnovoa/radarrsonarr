package com.denysnovoa.nzbmanager.common.framework.di

import com.denysnovoa.nzbmanager.common.framework.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class ApplicationModule(val app: BaseApplication) {
    @Provides @Singleton fun provideApp() = app

}