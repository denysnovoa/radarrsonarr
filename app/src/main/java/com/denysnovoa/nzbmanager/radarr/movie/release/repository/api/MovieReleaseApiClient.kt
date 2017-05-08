package com.denysnovoa.nzbmanager.radarr.movie.release.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel
import io.reactivex.Completable
import io.reactivex.Flowable

interface MovieReleaseApiClient {
    fun getReleases(id: Int): Flowable<List<MovieReleaseModel>>
    fun download(movieReleaseModel: MovieReleaseModel): Completable
}