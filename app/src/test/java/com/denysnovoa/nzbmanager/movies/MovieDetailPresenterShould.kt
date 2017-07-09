package com.denysnovoa.nzbmanager.movies

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieDetailView
import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.DeleteMovieUseCase
import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.GetMovieDetailUseCase
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter.MovieDetailPresenter
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

class MovieDetailPresenterShould {

    @Mock
    lateinit var view: MovieDetailView

    @Mock
    lateinit var errorLog: ErrorLog

    lateinit var presenter: MovieDetailPresenter

    @Mock
    lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Mock
    lateinit var deleteMovieUseCase: DeleteMovieUseCase

    @Mock
    lateinit var moviesViewMapper: MoviesViewMapper

    private val DELETE_FILES = false
    private val MOVIE_ID = 12222
    private val EXCLUDE_IMPORTS = false

    @Before
    fun setUp() {
        initMocks(this)
        presenter = MovieDetailPresenter(view, errorLog, getMovieDetailUseCase, moviesViewMapper, deleteMovieUseCase,
                Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun returnToMoviesWhenDeleteMovie() {
        `when`(deleteMovieUseCase.delete(MOVIE_ID, DELETE_FILES, EXCLUDE_IMPORTS)).thenReturn(Completable.complete())

        presenter.onDeleteMovie(MOVIE_ID, DELETE_FILES, EXCLUDE_IMPORTS)

        verify(deleteMovieUseCase).delete(MOVIE_ID, DELETE_FILES, EXCLUDE_IMPORTS)
        verify(view).returnToMoviesView()
    }

    @Test
    fun showErrorMessageWhenNoDeleteMovie() {
        `when`(deleteMovieUseCase.delete(MOVIE_ID, DELETE_FILES, EXCLUDE_IMPORTS)).thenReturn(Completable.error(Exception()))

        presenter.onDeleteMovie(MOVIE_ID, DELETE_FILES, EXCLUDE_IMPORTS)

        verify(deleteMovieUseCase).delete(MOVIE_ID, DELETE_FILES, EXCLUDE_IMPORTS)
        verify(view).showErrorDeleteMovie()

    }
}