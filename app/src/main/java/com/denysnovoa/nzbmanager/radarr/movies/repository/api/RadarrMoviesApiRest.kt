package com.denysnovoa.nzbmanager.radarr.movies.repository.api

import com.denysnovoa.nzbmanager.radarr.movies.repository.entity.MovieEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers

interface RadarrMoviesApiRest {

    @Headers("X-Api-Key: b5536e00243a4fd9ad002c53202fb771")
    @GET("api/movie")
    fun movies(): Flowable<List<MovieEntity>>
}