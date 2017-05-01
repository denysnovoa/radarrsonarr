package com.denysnovoa.nzbmanager.radarr.movie.list.view.screen

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.BaseActivity
import com.denysnovoa.nzbmanager.common.framework.startActivity
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movies.MoviesActivityModule
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.adapter.MovieItemAdapter
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel
import com.denysnovoa.nzbmanager.radarr.movie.list.view.presenter.MoviesPresenter
import com.denysnovoa.nzbmanager.settings.screen.SettingsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movies.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class MoviesActivity : BaseActivity(), MoviesView {
    val PARAMETER_MOVIE_ID: String = "PARAMETER_MOVIE_ID"

    @Inject
    lateinit var moviesPresenter: MoviesPresenter

    @Inject
    lateinit var picasso: Picasso

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(MoviesActivityModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        recyclerMovies.layoutManager = GridLayoutManager(this, 3)
        recyclerMovies.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_settings) {
            startActivity<SettingsActivity>()
        }

        return true
    }

    override fun onResume() {
        super.onResume()
        moviesPresenter.getLastMovies()
    }

    override fun onStop() {
        super.onStop()
        moviesPresenter.stop()
    }

    override fun showErrorLoadMovies() {
        toast(getString(R.string.error_load_movies))
    }

    override fun showMovies(movies: List<MovieViewModel>) {
        recyclerMovies.adapter = MovieItemAdapter(movies,picasso, { (id) ->
            val intent = Intent(this, MoviesActivity::class.java)
            intent.putExtra(PARAMETER_MOVIE_ID, id)
            startActivity(intent)
        })

        recyclerMovies.recycledViewPool.setMaxRecycledViews(0, 0)

    }
}
