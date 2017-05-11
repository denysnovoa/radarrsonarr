package com.denysnovoa.nzbmanager.radarr.movie.release.view.mapper

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel

interface MovieReleaseViewMapper {
    fun transform(movieRelease: List<MovieReleaseModel>): List<MovieReleaseViewModel>
    fun transform(movieRelease: MovieReleaseModel): MovieReleaseViewModel
    fun transform(movieRelease: MovieReleaseViewModel): MovieReleaseModel

}

