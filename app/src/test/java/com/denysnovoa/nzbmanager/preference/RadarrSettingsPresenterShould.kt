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
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
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
    private val EMPTY: String = ""

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

    private fun givenARadarrSettings(): RadarrSettingsModel {
        val radarrSettingsModel = RadarrSettingsModel(HOST_NAME, PORT, "1222222")
        val radarrSettingsViewModel = RadarrSettingsViewModel(HOST_NAME, PORT, "1222222")
        `when`(radarrSettingsViewMapper.transform(radarrSettingsViewModel)).thenReturn(radarrSettingsModel)
        `when`(saveRadarrSettingsUseCase.save(radarrSettingsModel)).thenReturn(Completable.complete())

        presenter.radarrSettings = radarrSettingsViewModel
        return radarrSettingsModel
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = RadarrSettingsPresenter(view, getRadarrSettingsUseCase, saveRadarrSettingsUseCase, radarrSettingsViewMapper,
                errorLog, Schedulers.trampoline(), Schedulers.trampoline())


    }
}