package com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieImageModel
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieImageViewModel

interface MovieImageViewMapper {
    fun transform(images: List<MovieImageModel>): List<MovieImageViewModel>
    fun transform(image: MovieImageModel): MovieImageViewModel
}