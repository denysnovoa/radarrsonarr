package com.denysnovoa.nzbmanager.radarr.movie.release.view.domain

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.MovieReleaseApiClient
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel
import io.reactivex.Flowable

class GetMovieReleaseUseCase(val movieReleaseApiClient: MovieReleaseApiClient) {
    fun get(id: Int): Flowable<List<MovieReleaseModel>> {
        return movieReleaseApiClient.getReleases(id)
    }
}