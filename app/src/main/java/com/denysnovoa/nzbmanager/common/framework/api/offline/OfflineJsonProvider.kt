package com.denysnovoa.nzbmanager.common.framework.api.offline

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.Type


class OfflineJsonProvider(val context: Context) : OfflineJson {

    override fun <T> get(nameFile: String, typeOfT: Type): T {
        val json = context.resources.assets.open(nameFile)
        return Gson().fromJson<T>(json.bufferedReader(), typeOfT)
    }
}