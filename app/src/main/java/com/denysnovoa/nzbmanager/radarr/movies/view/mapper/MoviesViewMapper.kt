package com.denysnovoa.nzbmanager.radarr.movies.view.mapper

import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MovieModel
import com.denysnovoa.nzbmanager.radarr.movies.view.model.MovieViewModel

class MoviesViewMapper(val imageViewMapper: MovieImageViewMapper) {
    fun transform(movie: MovieModel) = with(movie) {
        MovieViewModel(id,
                title,
                status,
                downloaded,
                monitored,
                imdbId,
                tmdbId,
                qualityProfileId,
                imageViewMapper.transform(images))
    }
}