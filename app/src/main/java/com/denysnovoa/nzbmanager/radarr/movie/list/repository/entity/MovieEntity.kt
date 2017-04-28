package com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity(
        val id: Int,
        val title: String,
        val status: String,
        val downloaded: Boolean,
        val monitored: Boolean,
        val imdbId: String?,
        val tmdbId: String?,
        val qualityProfileId: Int,
        @SerializedName("images") val images: List<MovieImageEntity>)

