package com.denysnovoa.nzbmanager.radarr.movies.repository.api

import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MovieModel
import io.reactivex.Flowable

class RadarrMoviesApiClient(val moviesApi: RadarrMoviesApiRest) {

    fun getMovies(): Flowable<List<MovieModel>> {
        return moviesApi.movies()
                .flatMapIterable { moviesEntity -> moviesEntity }
                .map { (title) -> MovieModel(title) }
                .toList()
                .toFlowable()
    }
}