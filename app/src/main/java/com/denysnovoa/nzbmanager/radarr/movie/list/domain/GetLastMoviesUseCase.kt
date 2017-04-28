package com.denysnovoa.nzbmanager.radarr.movie.list.domain

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.MoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import io.reactivex.Flowable

class GetLastMoviesUseCase(val radarrMoviesApiClient: MoviesApiClient) {
    fun get(): Flowable<List<MovieModel>> {
        return radarrMoviesApiClient.getMovies()
    }
}


