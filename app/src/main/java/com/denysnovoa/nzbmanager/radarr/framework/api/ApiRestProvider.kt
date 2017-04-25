package com.denysnovoa.nzbmanager.radarr.framework.api

import retrofit2.Retrofit

class ApiRestProvider(val apiUrl: String) : ApiRest {
    override fun <T> get(service: Class<T>): T {
        val builder = Retrofit.Builder().baseUrl(apiUrl)
                .build()

        return builder.create(service)
    }
}

