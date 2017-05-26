package com.denysnovoa.nzbmanager.settings.screen.domain

import com.denysnovoa.nzbmanager.settings.screen.repository.RadarrSettingsRepository
import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import io.reactivex.Single


class GetRadarrSettingsUseCase(val radarrSettingsRepository: RadarrSettingsRepository) {
    fun get(): Single<RadarrSettingsModel> {
        return radarrSettingsRepository.get()
    }
}


