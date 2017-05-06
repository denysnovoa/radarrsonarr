package com.denysnovoa.nzbmanager.common.framework.api

import com.denysnovoa.nzbmanager.BuildConfig
import com.denysnovoa.nzbmanager.common.framework.api.cache.ApiCacheProvider
import com.denysnovoa.nzbmanager.common.framework.api.cache.NetworkCacheInterceptor
import com.denysnovoa.nzbmanager.common.framework.api.cache.OfflineCacheInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class ApiOkHttpClient(val authenticationInterceptor: AuthenticationInterceptor,
                      val networkInterceptor: NetworkCacheInterceptor,
                      val offlineCacheInterceptor: OfflineCacheInterceptor,
                      val apiCacheProvider: ApiCacheProvider) {


    val connectionTimeOut = 360L

    fun getClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .addInterceptor(offlineCacheInterceptor)
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    }
            )
            .cache(apiCacheProvider.getCache())
            .connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
            .readTimeout(connectionTimeOut, TimeUnit.SECONDS)
            .writeTimeout(connectionTimeOut, TimeUnit.SECONDS)
            .build()
}

