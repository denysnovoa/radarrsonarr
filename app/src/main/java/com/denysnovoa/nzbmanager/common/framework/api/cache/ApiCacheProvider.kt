package com.denysnovoa.nzbmanager.common.framework.api.cache

import android.content.Context
import okhttp3.Cache
import java.io.File

class ApiCacheProvider(val cacheKey: String, val context: Context) {
    fun getCache(): Cache = Cache(File(context.cacheDir, cacheKey), 10 * 1024 * 1024)
}

