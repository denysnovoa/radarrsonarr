package com.denysnovoa.nzbmanager.radarr.movie.detail.domain

import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.api.MovieReleaseApiClient
import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.model.MovieReleaseModel
import io.reactivex.Flowable

class GetMovieReleaseUseCase(val movieReleaseApiClient: MovieReleaseApiClient) {
    fun get(id: Int): Flowable<List<MovieReleaseModel>> {
        return movieReleaseApiClient.getReleases(id)
    }
}