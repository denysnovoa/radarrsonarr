package com.denysnovoa.nzbmanager

import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.view.presenter.SettingsPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SettingsPresenterShould {

    lateinit var settingsPresenter: SettingsPresenter

    @Mock
    lateinit var getRadarrSettingsUseCase: GetRadarrSettingsUseCase

    @Test
    fun load_settings_radarr_if_is_save_in_repository() {
        settingsPresenter.onResume()

        verify(getRadarrSettingsUseCase).get()
    }

    @Before
    fun before() {
        initMocks(this)
        settingsPresenter = SettingsPresenter(getRadarrSettingsUseCase)

    }
}
