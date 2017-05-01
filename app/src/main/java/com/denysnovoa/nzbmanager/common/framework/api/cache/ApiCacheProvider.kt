package com.denysnovoa.nzbmanager.common.framework.api.cache

import android.content.Context
import okhttp3.Cache
import java.io.File

class ApiCacheProvider(val context: Context, val cacheKey: String) {
    fun getCache(): Cache = Cache(File(context.cacheDir, cacheKey), 10 * 1024 * 1024)
}

