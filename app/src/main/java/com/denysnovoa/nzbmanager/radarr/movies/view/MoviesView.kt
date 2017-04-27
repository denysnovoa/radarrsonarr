package com.denysnovoa.nzbmanager.radarr.movies.view

import com.denysnovoa.nzbmanager.radarr.movies.view.model.MovieModelView

interface MoviesView {
    fun showErrorLoadMovies()
    fun showMovies(movies: List<MovieModelView>)
}