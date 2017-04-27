package com.denysnovoa.nzbmanager.radarr.movies.view.model

data class MovieViewModel(val id: Int,
                          val title: String,
                          val status: String,
                          val downloaded: Boolean,
                          val monitored: Boolean,
                          val imdbId: String?,
                          val tmdbId: String?,
                          val qualityProfileId: Int,
                          val images: List<MovieImageViewModel>)