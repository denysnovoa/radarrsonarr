package com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieEntity
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel

interface MoviesMapper {
    fun transform(movie: MovieEntity): MovieModel
}