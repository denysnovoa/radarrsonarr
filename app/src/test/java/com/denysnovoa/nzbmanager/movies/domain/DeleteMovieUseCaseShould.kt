package com.denysnovoa.nzbmanager.movies.domain

import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.DeleteMovieUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.MoviesApiClient
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DeleteMovieUseCaseShould {

    private val DELETE_FILE: Boolean = true
    private val MOVIE_ID: Int = 12
    private val EXCLUDE_FROM_IMPORTS: Boolean = false

    @Mock
    lateinit var moviesApiClient: MoviesApiClient

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun callRepositoryToDeleteMovie() {
        val deleteMovieUseCase = DeleteMovieUseCase(moviesApiClient)

        deleteMovieUseCase.delete(MOVIE_ID, DELETE_FILE, EXCLUDE_FROM_IMPORTS)

        Mockito.verify(moviesApiClient).delete(MOVIE_ID, DELETE_FILE, EXCLUDE_FROM_IMPORTS)
    }
}