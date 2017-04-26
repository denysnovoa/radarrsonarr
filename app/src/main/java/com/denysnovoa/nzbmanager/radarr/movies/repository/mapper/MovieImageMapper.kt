package com.denysnovoa.nzbmanager.radarr.movies.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movies.repository.entity.MovieImageEntity
import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MovieImageModel

class MovieImageMapper {
    fun transform(images: List<MovieImageEntity>) = images.mapNotNull { transform(it) }
    fun transform(image: MovieImageEntity) = with(image) { MovieImageModel(coverType, url) }
}