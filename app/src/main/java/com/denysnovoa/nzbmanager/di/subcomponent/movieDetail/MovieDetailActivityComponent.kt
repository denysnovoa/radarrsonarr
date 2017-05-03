package com.denysnovoa.nzbmanager.di.subcomponent.movieDetail

import com.denysnovoa.nzbmanager.di.scope.ActivityScope
import com.denysnovoa.nzbmanager.di.subcomponent.movies.movieDetail.MovieDetailActivityModule
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen.MovieDetailActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(MovieDetailActivityModule::class))
interface MovieDetailActivityComponent {
    fun injectTo(activity: MovieDetailActivity)
}