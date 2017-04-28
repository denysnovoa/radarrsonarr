package com.denysnovoa.nzbmanager.di

import com.denysnovoa.nzbmanager.common.framework.BaseApplication
import com.denysnovoa.nzbmanager.di.modules.DomainModule
import com.denysnovoa.nzbmanager.di.modules.MapperModule
import com.denysnovoa.nzbmanager.di.modules.RepositoryModule
import com.denysnovoa.nzbmanager.di.subcomponent.movies.MoviesActivityComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movies.MoviesActivityModule
import javax.inject.Singleton

@Singleton
@dagger.Component(modules = arrayOf(
        ApplicationModule::class,
        DomainModule::class,
        RepositoryModule::class,
        MapperModule::class))

interface ApplicationComponent {
    fun inject(app: BaseApplication)
    fun inject(module: MoviesActivityModule): MoviesActivityComponent
}
