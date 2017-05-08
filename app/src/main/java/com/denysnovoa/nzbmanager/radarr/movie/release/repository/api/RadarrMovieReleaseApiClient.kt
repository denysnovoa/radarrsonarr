package com.denysnovoa.nzbmanager.radarr.movie.release.repository.api

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.mapper.MovieReleaseMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel
import io.reactivex.Flowable


class RadarrMovieReleaseApiClient(val movieReleaseApi: RadarrMovieReleaseApiRest,
                                  val movieReleaseMapper: MovieReleaseMapper) : MovieReleaseApiClient {

    override fun getReleases(id: Int): Flowable<List<MovieReleaseModel>>
            = movieReleaseApi.get(id, "releaseWeight", "asc")
            .flatMapIterable { it }
            .map(movieReleaseMapper::transform)
            .toList()
            .toFlowable()

    override fun download(movieReleaseModel: MovieReleaseModel) =
            movieReleaseApi.post(movieReleaseMapper.transform(movieReleaseModel))
}