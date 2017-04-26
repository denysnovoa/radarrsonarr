package com.denysnovoa.nzbmanager.radarr.movies.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movies.repository.entity.MovieEntity
import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MovieModel

class MoviesMapper(val imagesMapper: MovieImageMapper = MovieImageMapper()) {
    fun transform(movie: MovieEntity) = MovieModel(movie.id,
            movie.title,
            movie.status,
            movie.downloaded,
            movie.monitored,
            movie.imdbId,
            movie.tmdbId,
            movie.qualityProfileId,
            imagesMapper.transform(movie.images))
}

