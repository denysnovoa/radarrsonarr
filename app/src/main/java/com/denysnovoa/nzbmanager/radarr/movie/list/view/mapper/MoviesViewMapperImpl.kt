package com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel

class MoviesViewMapperImpl(val imageViewMapper: MovieImageViewMapper) : MoviesViewMapper {
    override fun transform(movie: MovieModel) = with(movie) {
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