package com.denysnovoa.nzbmanager.radarr.movie.detail.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.MovieReleaseEntity
import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.model.MovieReleaseModel

interface MovieReleaseMapper {
    fun transform(movie: MovieReleaseEntity): MovieReleaseModel
}
