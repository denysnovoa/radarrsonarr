package com.denysnovoa.nzbmanager.radarr.movie.release.view

import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.model.MovieReleaseModel
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.model.MovieReleaseViewModel

interface MovieReleaseViewMapper {
    fun transform(movieRelease: List<MovieReleaseModel>): List<MovieReleaseViewModel>
    fun transform(movieRelease: MovieReleaseModel): MovieReleaseViewModel
}

