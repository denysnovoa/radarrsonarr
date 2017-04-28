package com.denysnovoa.nzbmanager.radarr.movie.list.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieEntity
import io.reactivex.Flowable
import retrofit2.http.GET

interface RadarrMoviesApiRest {
    @GET("api/movie")
    fun movies(): Flowable<List<MovieEntity>>
}