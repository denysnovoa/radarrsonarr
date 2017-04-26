package com.denysnovoa.nzbmanager.radarr.movies.repository.api

import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MovieModel
import io.reactivex.Flowable

interface MoviesApiClient {
    fun getMovies(): Flowable<List<MovieModel>>
}