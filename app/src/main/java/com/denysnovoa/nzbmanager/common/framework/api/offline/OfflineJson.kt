package com.denysnovoa.nzbmanager.common.framework.api.offline

import java.lang.reflect.Type

interface OfflineJson {

    companion object {
        val MOVIE_DETAIL_JSON = "movie_detail.json"
        val MOVIES_JSON = "movies_response.json"
        val MOVIES_RELEASES_JSON = "movie_releases.json"
    }

    fun <T> get(nameFile: String, typeOfT: Type): T
}