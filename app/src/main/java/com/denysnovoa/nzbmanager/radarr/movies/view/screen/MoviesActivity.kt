package com.denysnovoa.nzbmanager.radarr.movies.view.screen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.radarr.framework.ApiKey
import com.denysnovoa.nzbmanager.radarr.framework.ApiUrl
import com.denysnovoa.nzbmanager.radarr.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.framework.api.ApiRestProvider
import com.denysnovoa.nzbmanager.radarr.framework.api.AuthenticationInterceptor
import com.denysnovoa.nzbmanager.radarr.framework.toast
import com.denysnovoa.nzbmanager.radarr.movies.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movies.domain.modelView.MovieView
import com.denysnovoa.nzbmanager.radarr.movies.repository.api.RadarrMoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movies.repository.api.RadarrMoviesApiRest
import com.denysnovoa.nzbmanager.radarr.movies.repository.mapper.MovieImageMapper
import com.denysnovoa.nzbmanager.radarr.movies.repository.mapper.MoviesMapper
import com.denysnovoa.nzbmanager.radarr.movies.view.MoviesPresenter
import com.denysnovoa.nzbmanager.radarr.movies.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movies.view.adapter.MovieItemAdapter
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity(), MoviesView {
    lateinit var moviesPresenter: MoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        moviesPresenter = MoviesPresenter(this,
                ErrorLog(),
                GetLastMoviesUseCase(RadarrMoviesApiClient(
                        ApiRestProvider(ApiUrl, AuthenticationInterceptor(ApiKey)).get(RadarrMoviesApiRest::class.java),
                        MoviesMapper(MovieImageMapper())))
        )

        recyclerMovies.layoutManager = GridLayoutManager(this, 1)
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

    override fun showMovies(it: List<MovieView>) {
        recyclerMovies.adapter = MovieItemAdapter(it)
    }
}
