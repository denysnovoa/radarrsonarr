package com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieImageModel

interface MovieImageViewMapper {
    fun getPoster(images: List<MovieImageModel>): String
    fun getBanner(images: List<MovieImageModel>): String
}