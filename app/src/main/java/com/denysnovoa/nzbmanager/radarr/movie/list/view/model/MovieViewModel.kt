package com.denysnovoa.nzbmanager.radarr.movie.list.view.model

data class MovieViewModel(val id: Int,
                          val title: String,
                          val status: String,
                          val downloaded: Boolean,
                          val monitored: Boolean,
                          val overview: String,
                          val imdbId: String?,
                          val tmdbId: String?,
                          val qualityProfileId: Int,
                          val imagePoster: String,
                          val imageBanner: String)