package com.denysnovoa.nzbmanager.common.framework.api.cache

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class NetworkCacheInterceptor : Interceptor {
    val CACHE_CONTROL = "Cache-Control"

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder().maxAge(2, TimeUnit.MINUTES).build()

        return response.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()

    }

}
