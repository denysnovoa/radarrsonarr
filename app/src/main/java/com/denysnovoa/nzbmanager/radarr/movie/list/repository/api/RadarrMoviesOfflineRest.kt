package com.denysnovoa.nzbmanager.radarr.movie.list.repository.api

import android.content.Context
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.entity.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable
import java.util.*

class RadarrMoviesOfflineRest(val context: Context) {

    fun getMovies(): Flowable<List<MovieEntity>> =
            Flowable.fromCallable({
                val moviesJson = context.resources.assets.open("movies_response.json")
                val tokenList = object : TypeToken<ArrayList<MovieEntity>>() {}.type

                Gson().fromJson<List<MovieEntity>>(moviesJson.bufferedReader(), tokenList)
            })
}