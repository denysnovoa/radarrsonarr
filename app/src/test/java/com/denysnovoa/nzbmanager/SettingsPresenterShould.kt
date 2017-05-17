package com.denysnovoa.nzbmanager

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.domain.SaveRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.mapper.RadarrSettingsViewMapper
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel
import com.denysnovoa.nzbmanager.settings.screen.view.presenter.RadarrSettingsPresenter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import java.io.IOException

class SettingsPresenterShould {

    lateinit var radarrSettingsPresenter: RadarrSettingsPresenter

    @Mock
    lateinit var getRadarrSettingsUseCase: GetRadarrSettingsUseCase

    @Mock
    lateinit var radarrSettingsViewMapper: RadarrSettingsViewMapper

    @Mock
    lateinit var saveRadarrSettingsUseCase: SaveRadarrSettingsUseCase

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

        radarrSettingsPresenter.onResume()

        verify(getRadarrSettingsUseCase).get()
        verify(radarrSettingsViewMapper).transform(radarrSettings)
        verify(view).showSettings(radarrSettingsView)
        verify(view, never()).showErrorLoadSettings()
    }

    @Test
    fun show_error_when_no_load() {
        val exception = IOException("Error test")
        given(getRadarrSettingsUseCase.get()).willReturn(Single.error { exception })

        radarrSettingsPresenter.onResume()

        verify(view).showErrorLoadSettings()
    }

    @Test
    fun on_stop_clear_subscriber() {

        radarrSettingsPresenter.compositeDisposable.add(TestSubscriber<RadarrSettingsModel>())

        radarrSettingsPresenter.onStop()

        assert(radarrSettingsPresenter.compositeDisposable.size() == 0)
    }

    @Before
    fun before() {
        initMocks(this)

        radarrSettingsPresenter = RadarrSettingsPresenter(view, getRadarrSettingsUseCase, saveRadarrSettingsUseCase,
                radarrSettingsViewMapper, errorLog, Schedulers.trampoline(), Schedulers.trampoline())

    }
}
