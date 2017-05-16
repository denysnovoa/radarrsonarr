package com.denysnovoa.nzbmanager.settings.screen.repository

import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import io.reactivex.Single

interface RadarrSettingsRepository {
    fun get(): Single<RadarrSettingsModel>
    fun save(radarrSettingsModel: RadarrSettingsModel)
}
