package com.denysnovoa.nzbmanager.settings.screen.repository

import android.content.Context
import com.denysnovoa.nzbmanager.BuildConfig
import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import io.reactivex.Completable
import io.reactivex.Single

class RadarrSettingsStorage(val context: Context) : RadarrSettingsRepository {

    companion object {
        val PREFERENCE_RADARR_API_KEY = "PREFERENCE_RADARR_API_KEY"
        val PREFERENCE_RADARR_API_HOST = "PREFERENCE_RADARR_API_HOST"
        val PREFERENCE_RADARR_API_PORT = "PREFERENCE_RADARR_API_PORT"
        val STRING_EMPTY = ""
    }

    private val DEFAULT_API_HOST = "example.ddns.net"

    var apiKey: String  by PreferenceStorageProvider(context, PREFERENCE_RADARR_API_KEY, STRING_EMPTY)
    var apiHost: String  by PreferenceStorageProvider(context, PREFERENCE_RADARR_API_HOST, DEFAULT_API_HOST)
    var apiPort: Int  by PreferenceStorageProvider(context, PREFERENCE_RADARR_API_PORT, 7878)

    init {

        if (BuildConfig.DEBUG) {
            apiKey = "b5536e00243a4fd9ad002c53202fb771"
            apiHost = "dnovoa20.ddns.net"
        }
    }

    override fun get(): Single<RadarrSettingsModel>
            = Single.just(RadarrSettingsModel(apiHost, apiPort, apiKey))

    override fun save(radarrSettingsModel: RadarrSettingsModel): Completable = Completable.fromAction {
        apiKey = radarrSettingsModel.apiKey
        apiHost = radarrSettingsModel.hostName
        apiPort = radarrSettingsModel.port
    }
}

