package com.denysnovoa.nzbmanager.common.framework.di.modules

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.RadarrMoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.RadarrMoviesApiRest
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapper
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRadarrMoviesApiClient(radarrMoviesApiRest: RadarrMoviesApiRest, moviesMapper: MoviesMapper)
            = RadarrMoviesApiClient(radarrMoviesApiRest, moviesMapper)
}