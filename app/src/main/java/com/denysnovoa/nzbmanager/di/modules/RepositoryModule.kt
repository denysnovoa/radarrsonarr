package com.denysnovoa.nzbmanager.di.modules

import android.content.Context
import com.denysnovoa.nzbmanager.di.qualifier.ApplicationQualifier
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.MoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.RadarrMoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.RadarrMoviesApiRest
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.RadarrMoviesOfflineRest
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.MovieReleaseApiClient
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.RadarrMovieReleaseApiClient
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.RadarrMovieReleaseApiRest
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.mapper.MovieReleaseMapper
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRadarrMoviesApiClient(radarrMoviesApiRest: RadarrMoviesApiRest, moviesMapper: MoviesMapper, radarrMoviesOfflineRepository: RadarrMoviesOfflineRest)
            : MoviesApiClient = RadarrMoviesApiClient(radarrMoviesApiRest, moviesMapper, radarrMoviesOfflineRepository)

    @Provides
    fun provideRadarrMovieReleaseApiClient(movieReleaseApiRest: RadarrMovieReleaseApiRest, movieReleaseMapper: MovieReleaseMapper)
            : MovieReleaseApiClient = RadarrMovieReleaseApiClient(movieReleaseApiRest, movieReleaseMapper)

    @Provides
    fun providerRadarrMoviesOfflineRest(@ApplicationQualifier context: Context) = RadarrMoviesOfflineRest(context)


}
