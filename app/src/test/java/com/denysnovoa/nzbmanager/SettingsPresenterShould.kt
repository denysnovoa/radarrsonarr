package com.denysnovoa.nzbmanager

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
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SettingsPresenterShould {

    lateinit var settingsPresenter: SettingsPresenter

    @Mock
    lateinit var getRadarrSettingsUseCase: GetRadarrSettingsUseCase

    @Mock
    lateinit var radarrSettingsViewMapper: RadarrSettingsViewMapper

    @Mock
    lateinit var view: SettingsView

    @Test
    fun load_settings_radarr_save_in_repository() {
        val radarrSettings = RadarrSettingsModel("test.net.com", 7878, "AKDSOASS122w")
        val radarrSettingsView = RadarrSettingsViewModel("test.net.com", 7878, "AKDSOASS122w")

        given(radarrSettingsViewMapper.transform(radarrSettings)).willReturn(radarrSettingsView)
        given(getRadarrSettingsUseCase.get()).willReturn(Single.just(radarrSettings))

        settingsPresenter.onResume()

        verify(getRadarrSettingsUseCase).get()
        verify(radarrSettingsViewMapper).transform(radarrSettings)
        verify(view).showSettings(radarrSettingsView)
        verify(view, never()).showErrorLoadSettings()
    }

    @Before
    fun before() {
        initMocks(this)
        settingsPresenter = SettingsPresenter(view, getRadarrSettingsUseCase
                , radarrSettingsViewMapper
                , Schedulers.trampoline()
                , Schedulers.trampoline())

    }
}
