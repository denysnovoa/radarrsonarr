package com.denysnovoa.nzbmanager.radarr.movie.detail.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.model.MovieReleaseModel
import io.reactivex.Flowable

interface MovieReleaseApiClient {
    fun getReleases(id: Int): Flowable<List<MovieReleaseModel>>
}