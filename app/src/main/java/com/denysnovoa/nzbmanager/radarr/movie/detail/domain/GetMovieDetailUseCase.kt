package com.denysnovoa.nzbmanager.radarr.movie.detail.domain

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.MoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import io.reactivex.Single

class GetMovieDetailUseCase(val moviesApiClient: MoviesApiClient) {
    fun get(id: Int): Single<MovieModel> {
        return moviesApiClient.getDetail(id)
    }
}