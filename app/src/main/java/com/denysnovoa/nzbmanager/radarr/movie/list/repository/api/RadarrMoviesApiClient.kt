package com.denysnovoa.nzbmanager.radarr.movie.list.repository.api

import com.denysnovoa.nzbmanager.common.framework.OfflineDebugMode
import com.denysnovoa.nzbmanager.common.framework.api.offline.OfflineJson
import com.denysnovoa.nzbmanager.common.framework.api.offline.OfflineJson.Companion.MOVIES_JSON
import com.denysnovoa.nzbmanager.common.framework.api.offline.OfflineJson.Companion.MOVIE_DETAIL_JSON
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieEntity
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException

class RadarrMoviesApiClient(val moviesApi: RadarrMoviesApiRest,
                            val movieMapper: MoviesMapper,
                            val offlineJson: OfflineJson) : MoviesApiClient {

    override fun getMovies(): Flowable<List<MovieModel>> =
            if (OfflineDebugMode) {
                Flowable.fromCallable {
                    offlineJson.get<List<MovieEntity>>(MOVIES_JSON, object : TypeToken<ArrayList<MovieEntity>>() {}.type)
                }.flatMapIterable { it }
                        .map(movieMapper::transform)
                        .toList()
                        .toFlowable()
            } else {
                moviesApi.movies()
                        .onErrorResumeNext { t: Throwable ->
                            when (t) {
                                is UnknownHostException ->
                                    Flowable.error<NetworkConnectionException>(NetworkConnectionException(t.message))
                                is HttpException ->
                                    if (t.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                                        Flowable.error<HttpNotFoundException>(HttpNotFoundException(t.message))
                                    }
                                is ConnectException ->
                                    Flowable.error<ApiUnknownHostException>(ApiUnknownHostException(t.message))
                            }

                            Flowable.error { t }
                        }
                        .flatMapIterable { it }
                        .map(movieMapper::transform)
                        .toList()
                        .toFlowable()
            }

    override fun getDetail(id: Int): Single<MovieModel> =
            if (OfflineDebugMode) {
                Single.fromCallable {
                    offlineJson.get<MovieEntity>(MOVIE_DETAIL_JSON, object : TypeToken<MovieEntity>() {}.type)
                }.map(movieMapper::transform)
            } else {
                moviesApi.getDetail(id)
                        .map(movieMapper::transform)
            }

}

class NetworkConnectionException(messageError: String?) : Throwable(messageError)
class ApiUnknownHostException(messageError: String?) : Throwable(messageError)
class HttpNotFoundException(messageError: String?) : Throwable(messageError)


