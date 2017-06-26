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
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
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
    private val HOST_EMPTY: String = ""


    @Test
    fun returnFalseWhenHostNameIsEmpty() {
        assertFalse(presenter.onHostChange(HOST_EMPTY))

        Mockito.verify(view).showHostRadarrSettingsIsRequired()
        Mockito.verifyZeroInteractions(saveRadarrSettingsUseCase)
    }

    @Test
    fun returnTrueAndUpdateHostWhenNoIsEmpty() {
        val radarrSettingsModel = RadarrSettingsModel(HOST_NAME, 7878, "1222222")
        val radarrSettingsViewModel = RadarrSettingsViewModel(HOST_NAME, 7878, "1222222")
        Mockito.`when`(getRadarrSettingsUseCase.get()).thenReturn(Single.just(radarrSettingsModel))
        Mockito.`when`(radarrSettingsViewMapper.transform(radarrSettingsModel)).thenReturn(radarrSettingsViewModel)
        Mockito.`when`(radarrSettingsViewMapper.transform(radarrSettingsViewModel)).thenReturn(radarrSettingsModel)
        Mockito.`when`(saveRadarrSettingsUseCase.save(radarrSettingsModel)).thenReturn(Completable.complete())

        presenter.onResume()

        Assert.assertTrue(presenter.onHostChange(HOST_NAME))

        Mockito.verify(saveRadarrSettingsUseCase).save(radarrSettingsModel)
        Mockito.verify(view, Mockito.never()).showHostRadarrSettingsIsRequired()
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = RadarrSettingsPresenter(view, getRadarrSettingsUseCase, saveRadarrSettingsUseCase, radarrSettingsViewMapper,
                errorLog, Schedulers.trampoline(), Schedulers.trampoline())


    }
}