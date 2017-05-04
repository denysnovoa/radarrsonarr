package com.denysnovoa.nzbmanager.radarr.movie.list.view.screen

import android.os.Bundle
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.BaseActivityAnko
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movies.MoviesActivityModule
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel
import com.denysnovoa.nzbmanager.radarr.movie.list.view.presenter.MoviesPresenter
import com.squareup.picasso.Picasso
import org.jetbrains.anko.toast
import javax.inject.Inject

class MoviesActivity : BaseActivityAnko<MoviesLayout>(), MoviesView {

    override val ui = MoviesLayout()

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
        // setContentView(R.layout.activity_movies)

        // recyclerMovies.layoutManager = GridLayoutManager(this, 3)
        //recyclerMovies.setHasFixedSize(true)

        // swipeMovies.setOnRefreshListener {
        //    moviesPresenter.onResume()
        //}
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.top_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        if (item?.itemId == R.id.action_settings) {
//            startActivity<SettingsActivity>()
//        }
//
//        return true
//    }

    override fun onResume() {
        super.onResume()
        moviesPresenter.onResume()
    }

    override fun onStop() {
        super.onStop()
        moviesPresenter.stop()
    }

    override fun showLoading() {
        //   swipeMovies.isRefreshing = true
    }

    override fun hideLoading() {
        // swipeMovies.isRefreshing = false
    }

    override fun showErrorLoadMovies() {
        toast(getString(R.string.error_load_movies))
    }

    override fun showMovies(movies: List<MovieViewModel>) {
//        recyclerMovies.adapter = MovieItemAdapter(movies, picasso, { (id) ->
//            val intent = Intent(this, MovieDetailActivity::class.java)
//            intent.putExtra(PARAMETER_MOVIE_ID, id)
//            startActivity(intent)
//        })
//
//        recyclerMovies.recycledViewPool.setMaxRecycledViews(0, 0)
//        swipeMovies.isRefreshing = false
    }
}
