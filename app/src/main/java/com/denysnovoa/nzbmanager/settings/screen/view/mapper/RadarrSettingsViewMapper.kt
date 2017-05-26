package com.denysnovoa.nzbmanager.settings.screen.view.mapper

import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel

interface RadarrSettingsViewMapper {
    fun transform(radarrSettings: RadarrSettingsModel): RadarrSettingsViewModel
    fun transform(radarrSettingsViewModel: RadarrSettingsViewModel): RadarrSettingsModel
}

