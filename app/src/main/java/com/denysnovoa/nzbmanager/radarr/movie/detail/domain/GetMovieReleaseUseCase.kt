package com.denysnovoa.nzbmanager.radarr.movie.detail.domain

import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.model.MovieReleaseModel
import io.reactivex.Flowable

class GetMovieReleaseUseCase {
    fun get(): Flowable<List<MovieReleaseModel>> {
        return Flowable.empty()
    }
}