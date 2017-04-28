package com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel

interface MoviesViewMapper {
    fun transform(movie: MovieModel): MovieViewModel
}