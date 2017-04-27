package com.denysnovoa.nzbmanager.common.framework.api

import com.denysnovoa.nzbmanager.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiRestProvider(val apiUrl: String, val authenticationInterceptor: AuthenticationInterceptor) : ApiRest {

    override fun <T> get(service: Class<T>): T {
        val builder = Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return builder.create(service)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(authenticationInterceptor)
                .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                        }
                ).build()
    }

}

