package com.denysnovoa.nzbmanager.radarr.movie.detail

import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.model.MovieReleaseModel

interface MovieReleaseView {
    fun showMovieReleases(movieReleases: List<MovieReleaseModel>)
    fun showErrorSearchReleases()
}