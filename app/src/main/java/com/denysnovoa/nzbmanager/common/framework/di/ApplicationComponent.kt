package com.denysnovoa.nzbmanager.common.framework.di

import com.denysnovoa.nzbmanager.common.framework.BaseApplication
import com.denysnovoa.nzbmanager.common.framework.di.modules.DomainModule
import com.denysnovoa.nzbmanager.common.framework.di.modules.MapperModule
import com.denysnovoa.nzbmanager.common.framework.di.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        DomainModule::class,
        RepositoryModule::class,
        MapperModule::class))

interface ApplicationComponent {
    fun inject(app: BaseApplication)
}