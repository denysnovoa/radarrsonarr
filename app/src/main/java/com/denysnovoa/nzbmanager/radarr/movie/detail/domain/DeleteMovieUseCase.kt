package com.denysnovoa.nzbmanager.radarr.movie.detail.domain

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.MoviesApiClient
import io.reactivex.Completable

class DeleteMovieUseCase(val moviesApiClient: MoviesApiClient) {
    fun delete(id: Int, deleteFile: Boolean, excludeFromImports: Boolean): Completable {
        return moviesApiClient.delete(id, deleteFile, excludeFromImports)
    }
}