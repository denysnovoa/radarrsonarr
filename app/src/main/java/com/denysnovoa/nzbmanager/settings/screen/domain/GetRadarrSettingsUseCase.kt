package com.denysnovoa.nzbmanager.settings.screen.domain

import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import io.reactivex.Single


class GetRadarrSettingsUseCase() {
    fun get(): Single<RadarrSettingsModel> {
        return Single.never()

    }
}


