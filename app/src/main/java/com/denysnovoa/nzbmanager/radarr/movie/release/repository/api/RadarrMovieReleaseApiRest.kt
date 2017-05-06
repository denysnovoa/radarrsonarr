package com.denysnovoa.nzbmanager.radarr.movie.release.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.MovieReleaseEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RadarrMovieReleaseApiRest {

    @GET("api/release")
    fun get(@Query("movieId") id: Int, @Query("sort_by") sort: String, @Query("order") order: String)
            : Flowable<List<MovieReleaseEntity>>
}