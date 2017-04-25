package com.denysnovoa.nzbmanager.radarr.movies.repository.api

import com.denysnovoa.nzbmanager.radarr.movies.repository.entity.MovieEntity
import retrofit2.http.GET
import retrofit2.http.Headers
import rx.Observable

interface RadarrMoviesApi {

    @Headers("X-Api-Key: b5536e00243a4fd9ad002c53202fb771")
    @GET("api/movie")
    fun movies(): Observable<MovieEntity>
}