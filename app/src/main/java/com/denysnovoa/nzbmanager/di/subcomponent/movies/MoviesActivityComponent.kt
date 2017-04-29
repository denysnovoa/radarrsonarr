package com.denysnovoa.nzbmanager.di.subcomponent.movies

import com.denysnovoa.nzbmanager.di.scope.ActivityScope
import com.denysnovoa.nzbmanager.radarr.movie.list.view.screen.MoviesActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(MoviesActivityModule::class))

interface MoviesActivityComponent {
    fun injectTo(activity: MoviesActivity)
}
