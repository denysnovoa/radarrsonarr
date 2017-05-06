package com.denysnovoa.nzbmanager.radarr.movie.detail.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.mapper.MovieReleaseMapper
import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.model.MovieReleaseModel
import io.reactivex.Flowable


class RadarrMovieReleaseApiClient(val movieReleaseApi: RadarrMovieReleaseApiRest,
                                  val movieReleaseMapper: MovieReleaseMapper) : MovieReleaseApiClient {

    override fun getReleases(id: Int): Flowable<List<MovieReleaseModel>>
            = movieReleaseApi.get(id, "releaseWeight", "asc")
            .flatMapIterable { it }
            .map(movieReleaseMapper::transform)
            .toList()
            .toFlowable()

}