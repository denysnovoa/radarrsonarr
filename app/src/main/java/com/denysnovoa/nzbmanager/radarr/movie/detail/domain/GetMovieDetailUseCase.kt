package com.denysnovoa.nzbmanager.radarr.movie.detail.domain

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import io.reactivex.Single

class GetMovieDetailUseCase() {
    fun get(id: Int): Single<MovieModel> {
        return Single.error { NotImplementedError() }
    }
}