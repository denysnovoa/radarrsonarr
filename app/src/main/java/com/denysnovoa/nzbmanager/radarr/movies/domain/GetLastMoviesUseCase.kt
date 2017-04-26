package com.denysnovoa.nzbmanager.radarr.movies.domain

import com.denysnovoa.nzbmanager.radarr.movies.repository.api.MoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MovieModel
import io.reactivex.Flowable

class GetLastMoviesUseCase(val radarrMoviesApiClient: MoviesApiClient) {
    fun get(): Flowable<List<MovieModel>> {
        return radarrMoviesApiClient.getMovies()
    }
}


