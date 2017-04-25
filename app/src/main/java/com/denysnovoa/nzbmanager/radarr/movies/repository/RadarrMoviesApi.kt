package com.denysnovoa.nzbmanager.radarr.movies.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RadarrMoviesApi {

    @Headers("X-Api-Key: b5536e00243a4fd9ad002c53202fb771")
    @GET("api/movie")
    fun movies(): Call<MovieEntity>
}