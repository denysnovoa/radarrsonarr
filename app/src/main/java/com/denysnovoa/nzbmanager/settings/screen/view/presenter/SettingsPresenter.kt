package com.denysnovoa.nzbmanager.settings.screen.view.presenter

import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase


class SettingsPresenter(val getRadarrSettingsUseCase: GetRadarrSettingsUseCase) {
    fun onResume() {
        getRadarrSettingsUseCase.get()
    }

}