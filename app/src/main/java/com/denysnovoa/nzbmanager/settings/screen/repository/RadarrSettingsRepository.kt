package com.denysnovoa.nzbmanager.settings.screen.repository

import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel

interface RadarrSettingsRepository {
    fun get(): RadarrSettingsModel
}
