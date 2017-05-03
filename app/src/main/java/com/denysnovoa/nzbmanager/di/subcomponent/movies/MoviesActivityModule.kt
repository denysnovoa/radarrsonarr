package com.denysnovoa.nzbmanager.di.subcomponent.movies

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.di.modules.ActivityModule
import com.denysnovoa.nzbmanager.di.scope.ActivityScope
import com.denysnovoa.nzbmanager.radarr.movie.list.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
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
                               moviesViewMapper: MoviesViewMapper)
            = MoviesPresenter(view, errorLog, getLastMoviesUseCase, moviesViewMapper)
}
