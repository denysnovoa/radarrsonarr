package com.denysnovoa.nzbmanager

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.mapper.RadarrSettingsViewMapper
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel
import com.denysnovoa.nzbmanager.settings.screen.view.presenter.SettingsPresenter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import java.io.IOException

class SettingsPresenterShould {

    lateinit var settingsPresenter: SettingsPresenter

    @Mock
    lateinit var getRadarrSettingsUseCase: GetRadarrSettingsUseCase

    @Mock
    lateinit var radarrSettingsViewMapper: RadarrSettingsViewMapper

    @Mock
    lateinit var view: SettingsView

    @Mock
    lateinit var errorLog: ErrorLog

    @Test
    fun load_settings_radarr_save_in_repository() {
        val HOST_NAME = "test.net.com"
        val HOST_PORT = 7878
        val API_KEY = "AKDSOASS122w"
        val radarrSettings = RadarrSettingsModel(HOST_NAME, HOST_PORT, API_KEY)
        val radarrSettingsView = RadarrSettingsViewModel(HOST_NAME, HOST_PORT, API_KEY)

        given(radarrSettingsViewMapper.transform(radarrSettings)).willReturn(radarrSettingsView)
        given(getRadarrSettingsUseCase.get()).willReturn(Single.just(radarrSettings))

        settingsPresenter.onResume()

        verify(getRadarrSettingsUseCase).get()
        verify(radarrSettingsViewMapper).transform(radarrSettings)
        verify(view).showSettings(radarrSettingsView)
        verify(view, never()).showErrorLoadSettings()
    }

    @Test
    fun show_error_when_no_load() {
        val exception = IOException("Error test")
        given(getRadarrSettingsUseCase.get()).willReturn(Single.error { exception })

        settingsPresenter.onResume()

        verify(view).showErrorLoadSettings()
    }

    @Before
    fun before() {
        initMocks(this)

        settingsPresenter = SettingsPresenter(view, getRadarrSettingsUseCase, radarrSettingsViewMapper,
                errorLog, Schedulers.trampoline(), Schedulers.trampoline())

    }
}
