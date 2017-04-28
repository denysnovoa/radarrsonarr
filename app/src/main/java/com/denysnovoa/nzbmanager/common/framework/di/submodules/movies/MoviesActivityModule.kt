package com.denysnovoa.nzbmanager.common.framework.di.submodules.movies

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.common.framework.di.modules.ActivityModule
import com.denysnovoa.nzbmanager.common.framework.di.scope.ActivityScope
import com.denysnovoa.nzbmanager.radarr.movie.list.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.list.view.presenter.MoviesPresenter
import com.denysnovoa.nzbmanager.radarr.movie.list.view.screen.MoviesActivity
import dagger.Module
import dagger.Provides

@Module
class MoviesActivityModule(activity: MoviesActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideMoviesView(): MoviesView = activity as MoviesView


    @Provides @ActivityScope
    fun provideMoviesPresenter(view: MoviesView,
                               errorLog: ErrorLog,
                               getLastMoviesUseCase: GetLastMoviesUseCase,
                               moviesViewMapper: MoviesViewMapperImpl)
            = MoviesPresenter(view, errorLog, getLastMoviesUseCase, moviesViewMapper)
}
