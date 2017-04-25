package com.denysnovoa.nzbmanager.radarr.movies.repository.api

import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MoviesModel
import rx.Observable

class RadarrMoviesApiClient(val moviesApi: RadarrMoviesApiRest) {

    fun getMovies(): Observable<List<MoviesModel>> {
        return moviesApi.movies()
                .flatMap { movies -> Observable.from(movies) }
                .map { (title) -> MoviesModel(title) }
                .toList()
    }
}