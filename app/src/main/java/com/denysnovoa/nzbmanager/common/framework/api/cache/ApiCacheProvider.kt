package com.denysnovoa.nzbmanager.common.framework.api.cache

import android.content.Context
import com.denysnovoa.nzbmanager.common.framework.ApiCacheKey
import okhttp3.Cache
import java.io.File

class ApiCacheProvider(val context: Context) {
    fun getCache(): Cache = Cache(File(context.cacheDir, ApiCacheKey), 10 * 1024 * 1024)
}

