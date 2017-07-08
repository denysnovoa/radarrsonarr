package com.denysnovoa.nzbmanager.radarr.movie.detail.domain

import io.reactivex.Completable

class DeleteMovieUseCase {
    fun delete(id: Int, deleteFile: Boolean = false): Completable {
        return Completable.complete()
    }
}