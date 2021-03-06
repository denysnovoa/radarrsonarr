package com.denysnovoa.nzbmanager.settings.screen.repository

import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import io.reactivex.Completable
import io.reactivex.Single

interface RadarrSettingsRepository {
    fun get(): Single<RadarrSettingsModel>
    fun save(radarrSettingsModel: RadarrSettingsModel): Completable
    var apiKey: String
    var apiHost: String
    var apiPort: Int
}
