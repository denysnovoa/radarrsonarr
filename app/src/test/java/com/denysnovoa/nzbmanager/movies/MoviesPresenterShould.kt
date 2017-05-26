package com.denysnovoa.nzbmanager.movies

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.list.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.view.presenter.MoviesPresenter
import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.repository.model.RadarrSettingsModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks


class MoviesPresenterShould {

    lateinit var presenter: MoviesPresenter

    @Mock
    lateinit var moviesViewMapper: MoviesViewMapper

    @Mock
    lateinit var getLastMoviesUseCase: GetLastMoviesUseCase

    @Mock
    lateinit var view: MoviesView

    @Mock
    lateinit var errorLog: ErrorLog

    @Mock
    lateinit var getRadarrSettingsUseCase: GetRadarrSettingsUseCase


    @Test
    fun show_message_to_configure_api_when_not_have_api_storage() {
        given(getRadarrSettingsUseCase.get()).willReturn(Single.just(RadarrSettingsModel("", 7878, "")))

        presenter.onResume()

        verify(view).showConfigureApi()
        verify(getLastMoviesUseCase, Mockito.never()).get()
    }

    @Before
    fun setUp() {
        initMocks(this)

        presenter = MoviesPresenter(view, errorLog, getLastMoviesUseCase, moviesViewMapper,
                Schedulers.trampoline(), Schedulers.trampoline())
    }
}