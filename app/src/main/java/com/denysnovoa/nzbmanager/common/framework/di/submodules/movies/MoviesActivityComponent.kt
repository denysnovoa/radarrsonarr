package com.denysnovoa.nzbmanager.common.framework.di.submodules.movies

import com.denysnovoa.nzbmanager.common.framework.di.scope.ActivityScope
import com.denysnovoa.nzbmanager.radarr.movie.list.view.screen.MoviesActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(MoviesActivityModule::class))

interface MoviesActivityComponent {
    fun injectTo(activity: MoviesActivity)
}
