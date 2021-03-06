package com.denysnovoa.nzbmanager.preference

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.domain.SaveRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.mapper.RadarrSettingsViewMapper
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel
import com.denysnovoa.nzbmanager.settings.screen.view.presenter.RadarrSettingsPresenter
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class RadarrSettingsPresenterShould {

    lateinit var presenter: RadarrSettingsPresenter

    @Mock
    lateinit var view: SettingsView

    @Mock
    lateinit var getRadarrSettingsUseCase: GetRadarrSettingsUseCase

    @Mock
    lateinit var saveRadarrSettingsUseCase: SaveRadarrSettingsUseCase

    @Mock
    lateinit var radarrSettingsViewMapper: RadarrSettingsViewMapper

    @Mock
    lateinit var errorLog: ErrorLog

    private val HOST_NAME = "dnovoa20.dnns.net"
    private val PORT = 7878
    private val EMPTY = ""
    private val API_KEY = "122222"

    @Test
    fun returnFalseWhenHostNameIsEmpty() {
        assertFalse(presenter.onHostChange(EMPTY))

        verify(view).showHostRadarrSettingsIsRequired()
        verifyZeroInteractions(saveRadarrSettingsUseCase)
    }

    @Test
    fun returnTrueAndUpdateHostWhenNoIsEmpty() {
        val radarrSettingsModel = givenARadarrSettings()

        Assert.assertTrue(presenter.onHostChange(HOST_NAME))

        verify(saveRadarrSettingsUseCase).save(radarrSettingsModel)
        verify(view, never()).showHostRadarrSettingsIsRequired()
    }

    @Test
    fun returnFalseWhenHostPortIsZero() {
        assertFalse(presenter.onPortChange(0))

        verify(view).showPortRadarrSettingsIsRequired()
        verifyZeroInteractions(saveRadarrSettingsUseCase)
    }

    @Test
    fun returnTrueAndUpdatePortWhenGreatZero() {
        val radarrSettingsModel = givenARadarrSettings()

        Assert.assertTrue(presenter.onPortChange(PORT))

        verify(saveRadarrSettingsUseCase).save(radarrSettingsModel)
        verify(view, never()).showPortRadarrSettingsIsRequired()
    }

    @Test
    fun returnFalseWhenApiKeyIsEmpty() {
        assertFalse(presenter.onApiKeyChange(EMPTY))

        verify(view).showApiKeyRadarrSettingsIsRequired()
        verifyZeroInteractions(saveRadarrSettingsUseCase)
    }

    @Test
    fun returnTrueAndUpdateApiKeyWhenNoEmpty() {
        val radarrSettingsModel = givenARadarrSettings()

        Assert.assertTrue(presenter.onApiKeyChange(API_KEY))

        verify(saveRadarrSettingsUseCase).save(radarrSettingsModel)
        verify(view, never()).showApiKeyRadarrSettingsIsRequired()
    }

    private fun givenARadarrSettings(): RadarrSettingsModel {
        val radarrSettingsModel = RadarrSettingsModel(HOST_NAME, PORT, API_KEY)
        val radarrSettingsViewModel = RadarrSettingsViewModel(HOST_NAME, PORT, API_KEY)
        `when`(radarrSettingsViewMapper.transform(radarrSettingsViewModel)).thenReturn(radarrSettingsModel)
        `when`(saveRadarrSettingsUseCase.save(radarrSettingsModel)).thenReturn(Completable.complete())

        presenter.radarrSettings = radarrSettingsViewModel
        return radarrSettingsModel
    }

    @Test
    fun load_radarr_settings_from_storage_when_onResume() {
        val radarrSettingsModel = RadarrSettingsModel(HOST_NAME, PORT, API_KEY)
        val radarrSettingsViewModel = RadarrSettingsViewModel(HOST_NAME, PORT, API_KEY)

        `when`(getRadarrSettingsUseCase.get()).thenReturn(Single.just(radarrSettingsModel))
        `when`(radarrSettingsViewMapper.transform(radarrSettingsModel)).thenReturn(radarrSettingsViewModel)

        presenter.onResume()

        assertEquals(radarrSettingsViewModel, presenter.radarrSettings)
        verify(getRadarrSettingsUseCase).get()
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = RadarrSettingsPresenter(view, getRadarrSettingsUseCase, saveRadarrSettingsUseCase, radarrSettingsViewMapper,
                errorLog, Schedulers.trampoline(), Schedulers.trampoline())


    }
}