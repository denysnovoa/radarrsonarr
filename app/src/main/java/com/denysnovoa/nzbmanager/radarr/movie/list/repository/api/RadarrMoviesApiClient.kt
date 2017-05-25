package com.denysnovoa.nzbmanager.radarr.movie.list.repository.api

import com.denysnovoa.nzbmanager.common.framework.OfflineDebugMode
import com.denysnovoa.nzbmanager.common.framework.api.offline.OfflineJson
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import io.reactivex.Flowable
import io.reactivex.Single

class RadarrMoviesApiClient(val moviesApi: RadarrMoviesApiRest,
                            val movieMapper: MoviesMapper,
                            val offlineJson: OfflineJson) : MoviesApiClient {

    override fun getMovies(): Flowable<List<MovieModel>> =
            if (OfflineDebugMode) {
                offlineJson.getMovies()
                        .flatMapIterable { it }
                        .map(movieMapper::transform)
                        .toList()
                        .toFlowable()
            } else {
                moviesApi.movies()
                        .flatMapIterable { it }
                        .map(movieMapper::transform)
                        .toList()
                        .toFlowable()
            }

    override fun getDetail(id: Int): Single<MovieModel> =
            if (OfflineDebugMode) {
                offlineJson.getMovie().map(movieMapper::transform)
            } else {
                moviesApi.getDetail(id).map(movieMapper::transform)
            }
}


