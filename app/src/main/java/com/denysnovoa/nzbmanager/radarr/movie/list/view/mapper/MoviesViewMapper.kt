package com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel

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