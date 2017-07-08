package com.denysnovoa.nzbmanager.movies.domain

import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.DeleteMovieUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.MoviesApiClient
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DeleteMovieUseCaseShould {

    private val DELETE_FILE: Boolean = false

    private val MOVIE_ID: Int = 12

    @Mock
    lateinit var moviesApiClient: MoviesApiClient

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun callRepositoryToDeleteMovie() {
        val deleteMovieUseCase = DeleteMovieUseCase(moviesApiClient)

        deleteMovieUseCase.delete(MOVIE_ID, DELETE_FILE)

        Mockito.verify(moviesApiClient).delete(MOVIE_ID, DELETE_FILE)
    }
}