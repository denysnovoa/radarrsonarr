package com.denysnovoa.nzbmanager.common.framework.api.provider

class ApiRestProvider(val apiUrl: String, val apiOkHttpClient: com.denysnovoa.nzbmanager.common.framework.api.ApiOkHttpClient) : ApiRest {

    override fun <T> get(service: Class<T>): T {
        val builder = retrofit2.Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(apiOkHttpClient.getClient())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .build()

        return builder.create(service)
    }
}

