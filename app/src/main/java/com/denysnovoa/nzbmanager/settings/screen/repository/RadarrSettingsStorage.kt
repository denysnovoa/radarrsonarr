package com.denysnovoa.nzbmanager.settings.screen.repository

import android.content.Context
import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import io.reactivex.Single

class RadarrSettingsStorage(val context: Context) : RadarrSettingsRepository {

    companion object {
        val PREFERENCE_RADARR_API_KEY = "PREFERENCE_RADARR_API_KEY"
        val PREFERENCE_RADARR_API_HOST = "PREFERENCE_RADARR_API_HOST"
        val PREFERENCE_RADARR_API_PORT = "PREFERENCE_RADARR_API_PORT"
        val PREFERENCE_RADARR_ENABLE = "PREFERENCE_RADARR_ENABLE"
    }

    var apiKey: String  by PreferenceStorageProvider(context, PREFERENCE_RADARR_API_KEY, "")
    var apiHost: String  by PreferenceStorageProvider(context, PREFERENCE_RADARR_API_HOST, "")
    var apiPort: Int  by PreferenceStorageProvider(context, PREFERENCE_RADARR_API_PORT, 7878)
    var enableService: Boolean  by PreferenceStorageProvider(context, PREFERENCE_RADARR_ENABLE, false)

    override fun get(): Single<RadarrSettingsModel>
            = Single.just(RadarrSettingsModel(apiKey, apiPort, apiHost))
}

