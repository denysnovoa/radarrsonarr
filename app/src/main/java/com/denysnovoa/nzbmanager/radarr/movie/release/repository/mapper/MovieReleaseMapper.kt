package com.denysnovoa.nzbmanager.radarr.movie.release.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.MovieReleaseEntity
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel

interface MovieReleaseMapper {
    fun transform(movie: MovieReleaseEntity): MovieReleaseModel
    fun transform(movie: MovieReleaseModel): MovieReleaseEntity

}
