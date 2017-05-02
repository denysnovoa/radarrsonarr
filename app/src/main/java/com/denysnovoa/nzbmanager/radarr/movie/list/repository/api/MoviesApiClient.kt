package com.denysnovoa.nzbmanager.radarr.movie.list.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import io.reactivex.Flowable
import io.reactivex.Single

interface MoviesApiClient {
    fun getMovies(): Flowable<List<MovieModel>>

    fun getDetail(id: Int): Single<MovieModel>
}