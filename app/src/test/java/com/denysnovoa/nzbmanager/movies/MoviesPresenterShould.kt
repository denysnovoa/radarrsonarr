package com.denysnovoa.nzbmanager.movies

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.list.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.view.presenter.MoviesPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks


class MoviesPresenterShould {

    @Mock
    lateinit var moviesViewMapper: MoviesViewMapper

    @Mock
    lateinit var getLastMoviesUseCase: GetLastMoviesUseCase

    @Mock
    lateinit var view: MoviesView

    @Mock
    lateinit var errorLog: ErrorLog

    @Test
    fun show_message_to_configure_api_when_not_have_api_storage() {
        val presenter = MoviesPresenter(view, errorLog, getLastMoviesUseCase, moviesViewMapper)
        presenter.onResume()

        verify(view).showConfigureApi()
    }

    @Before
    fun setUp() {
        initMocks(this)
    }
}