package com.denysnovoa.nzbmanager.radarr.movies.view

import com.denysnovoa.nzbmanager.radarr.movies.view.model.MovieViewModel

interface MoviesView {
    fun showErrorLoadMovies()
    fun showMovies(movies: List<MovieViewModel>)
}