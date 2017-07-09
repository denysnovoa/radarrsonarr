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
                            val error = when (t) {
                                is UnknownHostException -> ApiUnknownHostException(t.message)
                                is ConnectException -> ApiUnknownHostException(t.message)
                                is HttpException ->
                                    if (t.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                                        HttpNotFoundException(t.message)
                                    } else t
                                else -> t
                            }

                            Flowable.error { error }
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

    override fun delete(id: Int, deleteFiles: Boolean, excludeFromImportList: Boolean) =
            moviesApi.delete(id, deleteFiles, excludeFromImportList)
}

class NetworkConnectionException(messageError: String?) : Throwable(messageError)
class ApiUnknownHostException(messageError: String?) : Throwable(messageError)
class HttpNotFoundException(messageError: String?) : Throwable(messageError)


