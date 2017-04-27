package com.denysnovoa.nzbmanager.radarr.movies.view

import com.denysnovoa.nzbmanager.radarr.movies.view.modelView.MovieView

interface MoviesView {
    fun showErrorLoadMovies()
    fun showMovies(movies: List<MovieView>)
}