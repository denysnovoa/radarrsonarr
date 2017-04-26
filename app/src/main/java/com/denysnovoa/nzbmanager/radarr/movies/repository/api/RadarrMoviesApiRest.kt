package com.denysnovoa.nzbmanager.radarr.movies.repository.api

import com.denysnovoa.nzbmanager.radarr.movies.repository.entity.MovieEntity
import io.reactivex.Flowable
import retrofit2.http.GET

interface RadarrMoviesApiRest {
    @GET("api/movie")
    fun movies(): Flowable<List<MovieEntity>>
}