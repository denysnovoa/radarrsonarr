package com.denysnovoa.nzbmanager.di.modules

import com.denysnovoa.nzbmanager.radarr.movie.list.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.RadarrMoviesApiClient
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetLastMoviesUseCase(moviesApiClient: RadarrMoviesApiClient) = GetLastMoviesUseCase(moviesApiClient)
}
