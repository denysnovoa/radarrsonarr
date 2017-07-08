package com.denysnovoa.nzbmanager.radarr.movie.list.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface RadarrMoviesApiRest {
    @GET("api/movie")
    fun movies(): Flowable<List<MovieEntity>>

    @GET("api/movie/{id}")
    fun getDetail(@Path("id") id: Int): Single<MovieEntity>

    @DELETE("api/movie/{id}?deleteFiles={deleteFiles}&addExclusion={addExclusion}")
    fun delete(@Path("id") id: Int,
               @Path("deleteFiles") deleteFiles: Boolean,
               @Path("addExclusion") addExclusion: Boolean): Completable
}