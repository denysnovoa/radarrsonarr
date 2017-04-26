package com.denysnovoa.nzbmanager.radarr.movies.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movies.repository.entity.MovieEntity
import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MovieModel

class MoviesMapper(val imagesMapper: MovieImageMapper = MovieImageMapper()) {
    fun transform(movie: MovieEntity) = with(movie) {
        MovieModel(id,
                title,
                status,
                downloaded,
                monitored,
                imdbId,
                tmdbId,
                qualityProfileId,
                imagesMapper.transform(images))
    }
}

