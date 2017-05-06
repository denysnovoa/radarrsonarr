package com.denysnovoa.nzbmanager.di.subcomponent.movieRelease

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.di.modules.ActivityModule
import com.denysnovoa.nzbmanager.di.scope.ActivityScope
import com.denysnovoa.nzbmanager.radarr.movie.release.view.MovieReleaseView
import com.denysnovoa.nzbmanager.radarr.movie.release.view.domain.GetMovieReleaseUseCase
import com.denysnovoa.nzbmanager.radarr.movie.release.view.mapper.MovieReleaseViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.view.presenter.MovieReleasePresenter
import com.denysnovoa.nzbmanager.radarr.movie.release.view.screen.MovieReleaseActivity
import dagger.Module
import dagger.Provides


@Module
class MovieReleaseActivityModule(activity: MovieReleaseActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideMovieReleaseView(): MovieReleaseView = activity as MovieReleaseView


    @Provides @ActivityScope
    fun provideMovieReleasePresenter(view: MovieReleaseView,
                                     errorLog: ErrorLog,
                                     getLastMovieReleaseUseCase: GetMovieReleaseUseCase,
                                     movieReleaseViewMapper: MovieReleaseViewMapper
    )
            = MovieReleasePresenter(view, errorLog, getLastMovieReleaseUseCase, movieReleaseViewMapper)
}