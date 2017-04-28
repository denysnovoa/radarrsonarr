package com.denysnovoa.nzbmanager.radarr.movies.view.model

data class MovieViewModel(val id: Int,
                          val title: String,
                          val status: String,
                          val downloaded: Boolean,
                          val monitored: Boolean,
                          val imdbId: String?,
                          val tmdbId: String?,
                          val qualityProfileId: Int,
                          val images: List<MovieImageViewModel>) {

    val imagePoster = "http://dnovoa20.ddns.net:7878/api" + images.first { it.coverType == "poster" }.url
    //val imagePoster = "http://dnovoa20.ddns.net:7878/MediaCover/7192/poster.jpg"
    val imageBanner = "http://dnovoa20.ddns.net:7878" + images.first { it.coverType == "banner" }.url
}