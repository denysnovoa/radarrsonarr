package com.denysnovoa.nzbmanager.radarr.movies.repository.api

import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MoviesModel
import io.reactivex.Flowable

class RadarrMoviesApiClient(val moviesApi: RadarrMoviesApiRest) {

    fun getMovies(): Flowable<List<MoviesModel>> {
        return moviesApi.movies()
                .flatMapIterable { moviesEntity -> moviesEntity }
                .map { (title) -> MoviesModel(title) }
                .toList()
                .toFlowable()
    }
}