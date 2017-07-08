package com.denysnovoa.nzbmanager.common.framework.api

import com.denysnovoa.nzbmanager.settings.screen.repository.RadarrSettingsRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(val settingsStorage: RadarrSettingsRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestApiKey = chain.request()
                .newBuilder()
                .addHeader("X-Api-Key", settingsStorage.apiKey)
                .build()

        return chain.proceed(requestApiKey)
    }
}