package com.denysnovoa.nzbmanager.radarr.movie.release.view.screen

import com.denysnovoa.nzbmanager.common.framework.ui.BaseActivityAnko
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen.MovieDetailActivity
import com.denysnovoa.nzbmanager.radarr.movie.release.view.MovieReleaseView
import com.denysnovoa.nzbmanager.radarr.movie.release.view.adapter.MovieReleaseAdapterAnko
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel
import com.denysnovoa.nzbmanager.radarr.movie.release.view.presenter.MovieReleasePresenter
import org.jetbrains.anko.toast

class MovieReleaseActivity : BaseActivityAnko<MovieReleaseLayout>(), MovieReleaseView {

    override val ui = MovieReleaseLayout()

    lateinit var presenter: MovieReleasePresenter

    val adapter = MovieReleaseAdapterAnko { presenter.onReleaseClicked(it) }

    var movieId = 0

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        initializeToolbar()

        movieId = intent.extras.getInt(MovieDetailActivity.PARAMETER_MOVIE_ID)
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

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onResume() {
        super.onResume()

        presenter.onResume(movieId)
    }

    override fun showMovieReleases(movieReleases: List<MovieReleaseViewModel>) {
        adapter.items = movieReleases
    }

    override fun showErrorSearchReleases() {
        toast(com.denysnovoa.nzbmanager.R.string.error_load_movie_release)
    }
}
