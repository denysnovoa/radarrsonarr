package com.denysnovoa.nzbmanager.radarr.movies.view

import com.denysnovoa.nzbmanager.radarr.movies.domain.modelView.MovieView

interface MoviesView {
    fun showErrorLoadMovies()
    fun showMovies(it: List<MovieView>?)
}