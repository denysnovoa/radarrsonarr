package com.denysnovoa.nzbmanager.radarr.movie.release.view.screen

import android.app.ProgressDialog
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.BaseActivityAnko
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movieRelease.MovieReleaseActivityModule
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen.MovieDetailActivity
import com.denysnovoa.nzbmanager.radarr.movie.release.view.MovieReleaseView
import com.denysnovoa.nzbmanager.radarr.movie.release.view.adapter.MovieReleaseAdapterAnko
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel
import com.denysnovoa.nzbmanager.radarr.movie.release.view.presenter.MovieReleasePresenter
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast
import javax.inject.Inject

class MovieReleaseActivity : BaseActivityAnko<MovieReleaseLayout>(), MovieReleaseView {

    override val ui = MovieReleaseLayout()

    @Inject
    lateinit var presenter: MovieReleasePresenter

    val adapter = MovieReleaseAdapterAnko({ presenter.onReleaseClicked(it) })

    var movieId = 0

    lateinit var progressDialog: ProgressDialog

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(MovieReleaseActivityModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        initializeToolbar()

        movieId = intent.extras.getInt(MovieDetailActivity.PARAMETER_MOVIE_ID)

        ui.recycler.adapter = adapter
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

        if (adapter.itemCount == 0) {
            progressDialog = indeterminateProgressDialog(R.string.search_movie_releases)
            presenter.onResume(movieId)
        }
    }

    override fun showMovieReleases(movieReleases: List<MovieReleaseViewModel>) {
        adapter.items = movieReleases
    }

    override fun showErrorSearchReleases() {
        toast(R.string.error_load_movie_release)
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun showItemClicked() {
        toast(R.string.error_load_movie_release)
    }

    override fun hideLoading() {
        progressDialog.hide()
    }

    override fun showDownloadOk() {
        toast(R.string.ok_download_release)
    }

    override fun showErrorDownload() {
        toast(R.string.error_download_release)
    }
}
