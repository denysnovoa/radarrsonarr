package com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.BaseActivity
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movies.movieDetail.MovieDetailActivityModule
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieDetailView
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter.MovieDetailPresenter
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel
import org.jetbrains.anko.toast
import javax.inject.Inject

class MovieDetailActivity : BaseActivity(), MovieDetailView {
    @Inject
    lateinit var presenter: MovieDetailPresenter

    val movieid: Int = 0

    override fun injectDependencies(applicationComponent: ApplicationComponent) {

        applicationComponent.plus(MovieDetailActivityModule(this))
                .injectTo(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        initializeToolbar()

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener({ view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })
    }

    private fun initializeToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(movieid)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    override fun showDetail(movie: MovieViewModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorLoadMovie() {
        toast(getString(R.string.error_load_movie))
    }
}
