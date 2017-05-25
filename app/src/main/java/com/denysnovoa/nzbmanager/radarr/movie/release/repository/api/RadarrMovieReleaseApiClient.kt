package com.denysnovoa.nzbmanager.radarr.movie.release.repository.api

import com.denysnovoa.nzbmanager.common.framework.OfflineMode
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.mapper.MovieReleaseMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel
import io.reactivex.Flowable
import io.reactivex.Single

class RadarrMovieReleaseApiClient(val movieReleaseApi: RadarrMovieReleaseApiRest,
                                  val movieReleaseMapper: MovieReleaseMapper) : MovieReleaseApiClient {

    override fun getReleases(id: Int): Flowable<List<MovieReleaseModel>> =
            if (OfflineMode) {
                Flowable.empty()
            } else {
                movieReleaseApi.get(id, "releaseWeight", "asc")
                        .flatMapIterable { it }
                        .map(movieReleaseMapper::transform)
                        .toList()
                        .toFlowable()
            }
    

    override fun download(movieReleaseModel: MovieReleaseModel): Single<MovieReleaseModel>
            = movieReleaseApi.post(movieReleaseMapper.transform(movieReleaseModel))
            .map(movieReleaseMapper::transform)

}



