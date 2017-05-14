package com.denysnovoa.nzbmanager.radarr.movie.list.view.screen

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.BaseActivityAnko
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movies.MoviesActivityModule
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen.MovieDetailActivity
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen.MovieDetailActivity.Companion.PARAMETER_MOVIE_ID
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.adapter.MovieItemAdapterAnko
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel
import com.denysnovoa.nzbmanager.radarr.movie.list.view.presenter.MoviesPresenter
import com.denysnovoa.nzbmanager.settings.screen.view.screen.SettingsActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class MoviesActivity : BaseActivityAnko<MoviesLayout>(), MoviesView {

    override val ui = MoviesLayout()

    @Inject
    lateinit var presenter: MoviesPresenter

    @Inject
    lateinit var picasso: Picasso

    val adapter = MovieItemAdapterAnko { presenter.onMovieClicked(it) }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(MoviesActivityModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.recycler.adapter = adapter

        ui.swipe.setOnRefreshListener { presenter.onResume() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            startActivity<SettingsActivity>(); true
        }
        else -> true
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun showLoading() {
        ui.swipe.isRefreshing = true
    }

    override fun hideLoading() {
        ui.swipe.isRefreshing = false
    }

    override fun showErrorLoadMovies() {
        toast(getString(R.string.error_load_movies))
    }

    override fun goToMovieDetail(id: Int) {
        startActivity<MovieDetailActivity>(PARAMETER_MOVIE_ID to id)
    }

    override fun showMovies(movies: List<MovieViewModel>) {
        adapter.picasso = picasso
        adapter.items = movies

    }
}
