package com.denysnovoa.nzbmanager.settings.screen.repository

import android.content.Context
import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import io.reactivex.Single

class RadarrSettingsStorage(val context: Context) : RadarrSettingsRepository {

    companion object {
        val PREFERENCE_RADARR_API_KEY = "PREFERENCE_RADARR_API_KEY"
        val PREFERENCE_RADARR_API_HOST = "PREFERENCE_RADARR_API_HOST"
        val PREFERENCE_RADARR_API_PORT = "PREFERENCE_RADARR_API_PORT"
    }

    var apiKey: String  by PreferenceStorageProvider(context, PREFERENCE_RADARR_API_KEY, "b5536e00243a4fd9ad002c53202fb771")
    var apiHost: String  by PreferenceStorageProvider(context, PREFERENCE_RADARR_API_HOST, "dnovoa20.ddns.net")
    var apiPort: Int  by PreferenceStorageProvider(context, PREFERENCE_RADARR_API_PORT, 7878)

    override fun get(): Single<RadarrSettingsModel>
            = Single.just(RadarrSettingsModel(apiHost, apiPort, apiKey))
}

