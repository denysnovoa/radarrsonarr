package com.denysnovoa.nzbmanager.radarr.movie.release.view

import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel

interface MovieReleaseView {
    fun showMovieReleases(movieReleases: List<MovieReleaseViewModel>)
    fun showErrorSearchReleases()
    fun showLoading()
    fun hideLoading()
    fun showItemClicked()
}