package com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieImageEntity
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieImageModel

class MovieImageMapper {
    fun transform(images: List<MovieImageEntity>) = images.mapNotNull { transform(it) }
    fun transform(image: MovieImageEntity) = with(image) { MovieImageModel(coverType, url) }
}