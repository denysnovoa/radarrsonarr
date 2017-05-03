package com.denysnovoa.nzbmanager.di.subcomponent.movies.movieDetail

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.di.modules.ActivityModule
import com.denysnovoa.nzbmanager.di.scope.ActivityScope
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieDetailView
import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.GetMovieDetailUseCase
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter.MovieDetailPresenter
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen.MovieDetailActivity
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import dagger.Module
import dagger.Provides

@Module
class MovieDetailActivityModule(activity: MovieDetailActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideMovieDetailView(): MovieDetailView = activity as MovieDetailView

    @Provides @ActivityScope
    fun provideMovieDetailPresenter(view: MovieDetailView,
                                    errorLog: ErrorLog,
                                    getMovieDetailUseCase: GetMovieDetailUseCase,
                                    moviesViewMapper: MoviesViewMapper)
            = MovieDetailPresenter(view, errorLog, getMovieDetailUseCase, moviesViewMapper)

}

