package com.denysnovoa.nzbmanager.settings.screen.view.mapper

import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel

class RadarrSettingsViewMapperImpl : RadarrSettingsViewMapper {

    override fun transform(radarrSettingsModel: RadarrSettingsModel)
            = with(radarrSettingsModel) { RadarrSettingsViewModel(hostName, ip, apiKey) }
}