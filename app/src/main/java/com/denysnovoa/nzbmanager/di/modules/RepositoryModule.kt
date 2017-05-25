package com.denysnovoa.nzbmanager.di.modules

import android.content.Context
import com.denysnovoa.nzbmanager.common.framework.api.offline.OfflineJson
import com.denysnovoa.nzbmanager.di.qualifier.ApplicationQualifier
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.MoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.RadarrMoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.RadarrMoviesApiRest
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.MovieReleaseApiClient
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.RadarrMovieReleaseApiClient
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.RadarrMovieReleaseApiRest
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.mapper.MovieReleaseMapper
import com.denysnovoa.nzbmanager.settings.screen.repository.RadarrSettingsRepository
import com.denysnovoa.nzbmanager.settings.screen.repository.RadarrSettingsStorage
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRadarrMoviesApiClient(radarrMoviesApiRest: RadarrMoviesApiRest, moviesMapper: MoviesMapper, offlineRest: OfflineJson)
            : MoviesApiClient = RadarrMoviesApiClient(radarrMoviesApiRest, moviesMapper, offlineRest)

    @Provides
    fun provideRadarrMovieReleaseApiClient(movieReleaseApiRest: RadarrMovieReleaseApiRest, movieReleaseMapper: MovieReleaseMapper, offlineRest: OfflineJson)
            : MovieReleaseApiClient = RadarrMovieReleaseApiClient(movieReleaseApiRest, movieReleaseMapper, offlineRest)

    @Provides
    fun provideRadarrSettingsStorage(@ApplicationQualifier context: Context): RadarrSettingsRepository = RadarrSettingsStorage(context)

}
