package com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieImageEntity
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieImageModel


interface MovieImageMapper {
    fun transform(images: List<MovieImageEntity>): List<MovieImageModel>
    fun transform(image: MovieImageEntity): MovieImageModel
}
