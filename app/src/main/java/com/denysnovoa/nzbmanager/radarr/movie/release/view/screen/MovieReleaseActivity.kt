package com.denysnovoa.nzbmanager.radarr.movie.release.view.screen

import com.denysnovoa.nzbmanager.common.framework.ui.BaseActivityAnko
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.radarr.movie.release.view.MovieReleaseView
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel
import org.jetbrains.anko.toast

class MovieReleaseActivity : BaseActivityAnko<MovieReleaseLayout>(), MovieReleaseView {

    override val ui = MovieReleaseLayout()

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
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

    override fun showMovieReleases(movieReleases: List<MovieReleaseViewModel>) {

    }

    override fun showErrorSearchReleases() {
        toast(com.denysnovoa.nzbmanager.R.string.error_load_movie_release)
    }
}
