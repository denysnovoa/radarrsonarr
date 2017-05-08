package com.denysnovoa.nzbmanager.di

import com.denysnovoa.nzbmanager.common.framework.BaseApplication
import com.denysnovoa.nzbmanager.di.modules.DataModule
import com.denysnovoa.nzbmanager.di.modules.DomainModule
import com.denysnovoa.nzbmanager.di.modules.MapperModule
import com.denysnovoa.nzbmanager.di.modules.RepositoryModule
import com.denysnovoa.nzbmanager.di.subcomponent.movieDetail.MovieDetailActivityComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movieRelease.MovieReleaseActivityComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movieRelease.MovieReleaseActivityModule
import com.denysnovoa.nzbmanager.di.subcomponent.movies.MoviesActivityComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movies.MoviesActivityModule
import com.denysnovoa.nzbmanager.di.subcomponent.movies.movieDetail.MovieDetailActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        MapperModule::class,
        DomainModule::class,
        DataModule::class,
        RepositoryModule::class))

interface ApplicationComponent {
    fun inject(app: BaseApplication)
    fun plus(module: MoviesActivityModule): MoviesActivityComponent
    fun plus(module: MovieDetailActivityModule): MovieDetailActivityComponent
    fun plus(module: MovieReleaseActivityModule): MovieReleaseActivityComponent
}
