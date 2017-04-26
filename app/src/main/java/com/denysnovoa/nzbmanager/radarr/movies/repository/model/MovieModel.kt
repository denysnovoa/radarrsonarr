package com.denysnovoa.nzbmanager.radarr.movies.repository.model

data class MovieModel(
        val id: Int,
        val title: String,
        val status: String,
        val downloaded: Boolean,
        val monitored: Boolean,
        val imdbId: String,
        val tmdbId: String,
        val qualityProfileId: Int,
        val images: List<MovieImageModel>)

