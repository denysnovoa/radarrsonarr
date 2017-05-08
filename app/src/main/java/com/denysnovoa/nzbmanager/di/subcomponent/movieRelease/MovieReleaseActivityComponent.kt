package com.denysnovoa.nzbmanager.di.subcomponent.movieRelease

import com.denysnovoa.nzbmanager.di.scope.ActivityScope
import com.denysnovoa.nzbmanager.radarr.movie.release.view.screen.MovieReleaseActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(MovieReleaseActivityModule::class))
interface MovieReleaseActivityComponent {
    fun injectTo(activity: MovieReleaseActivity)
}
