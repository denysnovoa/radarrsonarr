package com.denysnovoa.nzbmanager.common.framework.api.provider

import com.denysnovoa.nzbmanager.common.framework.api.ApiOkHttpClient
import com.denysnovoa.nzbmanager.settings.screen.repository.RadarrSettingsRepository
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiRestProvider(val settingsStorage: RadarrSettingsRepository, val apiOkHttpClient: ApiOkHttpClient) : ApiRest {

    override fun <T> get(service: Class<T>): T {
        val builder = retrofit2.Retrofit.Builder()
                .baseUrl("http://${settingsStorage.apiHost}:${settingsStorage.apiPort}/")
                .client(apiOkHttpClient.getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return builder.create(service)
    }

}

