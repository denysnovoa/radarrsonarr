package com.denysnovoa.nzbmanager.common.framework.api.offline

import android.content.Context
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieEntity
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.MovieReleaseEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable


class OfflineJsonProvider(val context: Context) : OfflineJson {

    override fun <T> get(nameFile: String): T {
        val json = context.resources.assets.open(nameFile)
        val tokenType = object : TypeToken<T>() {}.type
        return Gson().fromJson<T>(json.bufferedReader(), tokenType)
    }

    override fun getMovies(): Flowable<List<MovieEntity>> =
            Flowable.fromCallable {
                val moviesJson = context.resources.assets.open("movies_response.json")
                val tokenList = object : TypeToken<ArrayList<MovieEntity>>() {}.type

                Gson().fromJson<List<MovieEntity>>(moviesJson.bufferedReader(), tokenList)
            }

    override fun getReleases(): Flowable<List<MovieReleaseEntity>> =
            Flowable.fromCallable({
                val moviesJson = context.resources.assets.open("movie_detail.json")
                val tokenList = object : TypeToken<java.util.ArrayList<MovieReleaseEntity>>() {}.type

                Gson().fromJson<List<MovieReleaseEntity>>(moviesJson.bufferedReader(), tokenList)
            })

}