package com.denysnovoa.nzbmanager.radarr.movie.list.view

import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel

interface MoviesView {
    fun showErrorLoadMovies()
    fun showMovies(movies: List<MovieViewModel>)
    fun showLoading()
    fun hideLoading()
    fun goToMovieDetail(id: Int)
    fun showConfigureApi()
    fun showErrorLoadApiSettings()
}