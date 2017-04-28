package com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieImageEntity
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieImageModel

class MovieImageMapperImpl : MovieImageMapper {
    override fun transform(images: List<MovieImageEntity>) = images.mapNotNull { transform(it) }
    override fun transform(image: MovieImageEntity) = with(image) { MovieImageModel(coverType, url) }
}