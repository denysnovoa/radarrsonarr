package com.denysnovoa.nzbmanager.radarr.framework.api

import com.denysnovoa.nzbmanager.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRestProvider(val apiUrl: String) : ApiRest {
    override fun <T> get(service: Class<T>): T {
        val builder = Retrofit.Builder()
                .client(getClient())
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return builder.create(service)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                        }
                ).build()
    }

}

