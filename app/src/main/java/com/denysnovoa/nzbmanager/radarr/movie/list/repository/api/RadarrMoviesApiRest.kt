package com.denysnovoa.nzbmanager.radarr.movie.list.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieEntity
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RadarrMoviesApiRest {
    @GET("api/movie")
    fun movies(): Flowable<List<MovieEntity>>

    @GET("api/movie/{id}")
    fun getDetail(@Path("id") id: Int): Single<MovieEntity>

}