package com.denysnovoa.nzbmanager.radarr.movie.list.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import io.reactivex.Flowable

class RadarrMoviesApiClient(val moviesApi: RadarrMoviesApiRest, val movieMapper: MoviesMapper) : MoviesApiClient {
    override fun getMovies(): Flowable<List<MovieModel>> = moviesApi.movies()
            .flatMapIterable { it }
            .map(movieMapper::transform)
            .toList()
            .toFlowable()
}

