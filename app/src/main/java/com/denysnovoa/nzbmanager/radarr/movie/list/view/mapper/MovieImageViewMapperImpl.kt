package com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieImageModel
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieImageViewModel

class MovieImageViewMapperImpl : MovieImageViewMapper {

    override fun getPoster(images: List<MovieImageModel>): String {
        val image = images.firstOrNull { it.coverType == "poster" }

        if (!image?.url.isNullOrEmpty()) {
            val split = image?.url?.split("poster")

            return "http://dnovoa20.ddns.net:7878/api" + split?.get(0) + "poster-500" + split?.get(1)
        }
        return ""
    }

    override fun getBanner(images: List<MovieImageModel>): String {
        val image = images.firstOrNull { it.coverType == "banner" }

        if (!image?.url.isNullOrEmpty()) {
            return "http://dnovoa20.ddns.net:7878/api" + image?.url
        }

        return ""
    }

    fun transform(images: List<MovieImageModel>) = images.mapNotNull { transform(it) }
    fun transform(image: MovieImageModel) = with(image) { MovieImageViewModel(coverType, url) }

}