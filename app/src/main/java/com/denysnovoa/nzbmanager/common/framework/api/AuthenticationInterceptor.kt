package com.denysnovoa.nzbmanager.common.framework.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestApiKey = chain.request()
                .newBuilder()
                .addHeader("X-Api-Key", apiKey)
                .build()

        return chain.proceed(requestApiKey)
    }
}