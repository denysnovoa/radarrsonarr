package com.denysnovoa.nzbmanager.di.subcomponent.movies

import com.denysnovoa.nzbmanager.radarr.movie.list.view.screen.MoviesActivity

@com.denysnovoa.nzbmanager.di.scope.ActivityScope
@dagger.Subcomponent(modules = arrayOf(MoviesActivityModule::class))

interface MoviesActivityComponent {
    fun injectTo(activity: MoviesActivity)
}
