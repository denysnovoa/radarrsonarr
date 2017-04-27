package com.denysnovoa.nzbmanager.common.framework.api.cache

import com.denysnovoa.nzbmanager.common.framework.api.NetworkConnection
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class OfflineCacheInterceptor(val networkConnection: NetworkConnection) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (networkConnection.hasNetwork()) {
            val cacheControl = CacheControl.Builder().maxStale(7, TimeUnit.DAYS)
                    .build()

            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build()
        }

        return chain.proceed(request)
    }
}