package com.denysnovoa.nzbmanager.common.framework.api.offline

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieEntity
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.MovieReleaseEntity
import io.reactivex.Flowable

interface OfflineJson {
    fun <T> get(nameFile: String): T
    fun getMovies(): Flowable<List<MovieEntity>>
    fun getReleases(): Flowable<List<MovieReleaseEntity>>
}