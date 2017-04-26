package com.denysnovoa.nzbmanager.radarr.movies.domain

import com.denysnovoa.nzbmanager.radarr.movies.domain.modelView.MovieView
import com.denysnovoa.nzbmanager.radarr.movies.repository.api.MoviesApiClient
import io.reactivex.Flowable

class GetLastMoviesUseCase(val radarrMoviesApiClient: MoviesApiClient) {

    fun get(): Flowable<List<MovieView>> {
        return radarrMoviesApiClient.getMovies()
                .flatMapIterable { moviesModel -> moviesModel }
                .map { (tittle) -> MovieView(tittle) }
                .toList()
                .toFlowable()
    }
}


