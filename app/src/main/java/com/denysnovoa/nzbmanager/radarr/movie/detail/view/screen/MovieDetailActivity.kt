package com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.BaseActivity
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movies.movieDetail.MovieDetailActivityModule
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieDetailView
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter.MovieDetailPresenter
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class MovieDetailActivity : BaseActivity(), MovieDetailView {

    companion object {
        val PARAMETER_MOVIE_ID: String = "PARAMETER_MOVIE_ID"
    }

    @Inject
    lateinit var presenter: MovieDetailPresenter
    var movieId: Int = 0

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(MovieDetailActivityModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        initializeToolbar()

        movieId = intent.extras.getInt(PARAMETER_MOVIE_ID)

        fab.setOnClickListener({ view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbar_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(movieId)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    override fun showDetail(movie: MovieViewModel) {
        title = movie.title
        with(movie) {
            movie_overview.text = overview
        }
    }

    override fun showErrorLoadMovie() {
        toast(getString(R.string.error_load_movie))
    }
}
