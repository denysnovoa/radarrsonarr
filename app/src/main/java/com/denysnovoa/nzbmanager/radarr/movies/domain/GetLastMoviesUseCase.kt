package com.denysnovoa.nzbmanager.radarr.movies.domain

import com.denysnovoa.nzbmanager.radarr.movies.domain.modelView.MoviesView
import com.denysnovoa.nzbmanager.radarr.movies.repository.api.RadarrMoviesApiClient
import io.reactivex.Flowable

class GetLastMoviesUseCase(val radarrMoviesApiClient: RadarrMoviesApiClient) {

    fun get(): Flowable<List<MoviesView>> {
        return radarrMoviesApiClient.getMovies()
                .flatMapIterable { moviesModel -> moviesModel }
                .map { (tittle) -> MoviesView(tittle) }
                .toList()
                .toFlowable()
    }
}


