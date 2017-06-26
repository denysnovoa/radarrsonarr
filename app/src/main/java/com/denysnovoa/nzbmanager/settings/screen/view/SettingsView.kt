package com.denysnovoa.nzbmanager.settings.screen.view

import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel

interface SettingsView {
    fun showErrorLoadSettings()
    fun showSettings(radarrSettings: RadarrSettingsViewModel)
    fun showHostRadarrSettingsIsRequired()
    fun showPortRadarrSettingsIsRequired()
}
