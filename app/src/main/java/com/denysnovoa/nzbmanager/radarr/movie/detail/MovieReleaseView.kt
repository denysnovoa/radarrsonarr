package com.denysnovoa.nzbmanager.radarr.movie.detail

import com.denysnovoa.nzbmanager.radarr.movie.detail.view.model.MovieReleaseViewModel

interface MovieReleaseView {
    fun showMovieReleases(movieReleases: List<MovieReleaseViewModel>)
    fun showErrorSearchReleases()
}