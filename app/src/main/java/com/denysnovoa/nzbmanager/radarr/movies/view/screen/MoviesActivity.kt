package com.denysnovoa.nzbmanager.radarr.movies.view.screen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.radarr.framework.ApiUrl
import com.denysnovoa.nzbmanager.radarr.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.framework.api.ApiRestProvider
import com.denysnovoa.nzbmanager.radarr.framework.toast
import com.denysnovoa.nzbmanager.radarr.movies.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movies.domain.modelView.MovieView
import com.denysnovoa.nzbmanager.radarr.movies.repository.api.RadarrMoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movies.repository.api.RadarrMoviesApiRest
import com.denysnovoa.nzbmanager.radarr.movies.view.MoviesPresenter
import com.denysnovoa.nzbmanager.radarr.movies.view.MoviesView
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity(), MoviesView {
    var moviesPresenter: MoviesPresenter = null!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        moviesPresenter = MoviesPresenter(this, ErrorLog(),
                GetLastMoviesUseCase(RadarrMoviesApiClient(ApiRestProvider(ApiUrl).get(RadarrMoviesApiRest::class.java))))

        recyclerMovies.layoutManager = GridLayoutManager(this, 2)
    }

    override fun showErrorLoadMovies() {
        toast(getString(R.string.error_load_movies))
    }

    override fun showMovies(it: List<MovieView>?) {

        recyclerMovies.adapter =

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
