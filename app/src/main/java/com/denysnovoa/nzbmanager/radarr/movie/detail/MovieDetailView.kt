package com.denysnovoa.nzbmanager.radarr.movie.detail

import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel

interface MovieDetailView {
    fun  showDetail(movie: MovieViewModel)
    fun  showErrorLoadMovie()
}