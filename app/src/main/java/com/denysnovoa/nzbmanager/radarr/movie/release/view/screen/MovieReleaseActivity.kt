package com.denysnovoa.nzbmanager.radarr.movie.release.view.screen

import com.denysnovoa.nzbmanager.radarr.movie.release.view.MovieReleaseView
import org.jetbrains.anko.toast

class MovieReleaseActivity : com.denysnovoa.nzbmanager.common.framework.ui.BaseActivityAnko<MovieReleaseLayout>(), MovieReleaseView {

    override val ui = com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen.MovieReleaseLayout()

    override fun injectDependencies(applicationComponent: com.denysnovoa.nzbmanager.di.ApplicationComponent) {
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        initializeToolbar()
    }

    fun initializeToolbar() {
        setSupportActionBar(ui.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem?): Boolean {
        finish()
        return true
    }

    override fun showMovieReleases(movieReleases: List<com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel>) {

    }

    override fun showErrorSearchReleases() {
        toast(com.denysnovoa.nzbmanager.R.string.error_load_movie_release)
    }
}
