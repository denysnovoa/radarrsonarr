package com.denysnovoa.nzbmanager.radarr.movies.view.screen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.*
import com.denysnovoa.nzbmanager.common.framework.api.ApiOkHttpClient
import com.denysnovoa.nzbmanager.common.framework.api.AuthenticationInterceptor
import com.denysnovoa.nzbmanager.common.framework.api.NetworkConnection
import com.denysnovoa.nzbmanager.common.framework.api.cache.ApiCacheProvider
import com.denysnovoa.nzbmanager.common.framework.api.cache.NetworkCacheInterceptor
import com.denysnovoa.nzbmanager.common.framework.api.cache.OfflineCacheInterceptor
import com.denysnovoa.nzbmanager.common.framework.api.provider.ApiRestProvider
import com.denysnovoa.nzbmanager.radarr.movies.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movies.repository.api.RadarrMoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movies.repository.api.RadarrMoviesApiRest
import com.denysnovoa.nzbmanager.radarr.movies.repository.mapper.MovieImageMapper
import com.denysnovoa.nzbmanager.radarr.movies.repository.mapper.MoviesMapper
import com.denysnovoa.nzbmanager.radarr.movies.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movies.view.adapter.MovieItemAdapter
import com.denysnovoa.nzbmanager.radarr.movies.view.model.MovieModelView
import com.denysnovoa.nzbmanager.radarr.movies.view.presenter.MoviesPresenter
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity(), MoviesView {
    lateinit var moviesPresenter: MoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        moviesPresenter = MoviesPresenter(this,
                ErrorLog(),
                GetLastMoviesUseCase(RadarrMoviesApiClient(ApiRestProvider(ApiUrl,
                        ApiOkHttpClient(AuthenticationInterceptor(ApiKey),
                                NetworkCacheInterceptor(),
                                OfflineCacheInterceptor(NetworkConnection(baseContext)), ApiCacheProvider(ApiCacheKey, baseContext))
                ).get(RadarrMoviesApiRest::class.java), MoviesMapper(MovieImageMapper()))
                )
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

    override fun showMovies(movies: List<MovieModelView>) {
        recyclerMovies.adapter = MovieItemAdapter(movies)
    }
}
