package com.denysnovoa.nzbmanager.radarr.movies.view.mapper

import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MovieImageModel
import com.denysnovoa.nzbmanager.radarr.movies.view.model.MovieImageViewModel

class MovieImageViewMapper {
    fun transform(images: List<MovieImageModel>) = images.mapNotNull { transform(it) }
    fun transform(image: MovieImageModel) = with(image) { MovieImageViewModel(coverType, url) }

}