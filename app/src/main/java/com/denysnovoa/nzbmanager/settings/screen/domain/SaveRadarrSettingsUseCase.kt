package com.denysnovoa.nzbmanager.settings.screen.domain

import com.denysnovoa.nzbmanager.settings.screen.repository.RadarrSettingsRepository
import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel

class SaveRadarrSettingsUseCase(val radarrSettingsRepository: RadarrSettingsRepository) {
    fun save(radarrSettingsModel: RadarrSettingsModel) = radarrSettingsRepository.save(radarrSettingsModel)
}