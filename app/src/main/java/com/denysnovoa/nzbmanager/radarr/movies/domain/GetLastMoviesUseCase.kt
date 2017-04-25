package com.denysnovoa.nzbmanager.radarr.movies.domain

import com.denysnovoa.nzbmanager.radarr.movies.domain.modelView.MoviesView
import com.denysnovoa.nzbmanager.radarr.movies.repository.api.RadarrMoviesApiClient
import rx.Observable

class GetLastMoviesUseCase(val radarrMoviesApiClient: RadarrMoviesApiClient) {

    fun get(): Observable<List<MoviesView>> {
        return radarrMoviesApiClient.getMovies()
                .flatMap { movies -> Observable.from(movies) }
                .map { (tittle) -> MoviesView(tittle) }
                .toList()
    }
}


