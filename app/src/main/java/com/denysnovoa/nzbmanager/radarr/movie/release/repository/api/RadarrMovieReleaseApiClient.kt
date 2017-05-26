package com.denysnovoa.nzbmanager.radarr.movie.release.repository.api

import com.denysnovoa.nzbmanager.common.framework.OfflineDebugMode
import com.denysnovoa.nzbmanager.common.framework.api.offline.OfflineJson
import com.denysnovoa.nzbmanager.common.framework.api.offline.OfflineJson.Companion.MOVIES_RELEASES_JSON
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.MovieReleaseEntity
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.mapper.MovieReleaseMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable
import io.reactivex.Single

class RadarrMovieReleaseApiClient(val movieReleaseApi: RadarrMovieReleaseApiRest,
                                  val movieReleaseMapper: MovieReleaseMapper,
                                  val offlineJson: OfflineJson) : MovieReleaseApiClient {

    override fun getReleases(id: Int): Flowable<List<MovieReleaseModel>> =
            if (OfflineDebugMode) {
                Flowable.fromCallable {
                    offlineJson.get<List<MovieReleaseEntity>>(MOVIES_RELEASES_JSON, object : TypeToken<List<MovieReleaseEntity>>() {}.type)
                }.flatMapIterable { it }
                        .map(movieReleaseMapper::transform)
                        .toList()
                        .toFlowable()
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



