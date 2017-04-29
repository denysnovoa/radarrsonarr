package com.denysnovoa.nzbmanager.common.framework.api.cache

import okhttp3.Interceptor

class NetworkCacheInterceptor : Interceptor {
    val CACHE_CONTROL = "Cache-Control"

    override fun intercept(chain: okhttp3.Interceptor.Chain): okhttp3.Response {
        val response = chain.proceed(chain.request())

        val cacheControl = okhttp3.CacheControl.Builder().maxAge(2, java.util.concurrent.TimeUnit.MINUTES).build()

        return response.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()

    }

}
