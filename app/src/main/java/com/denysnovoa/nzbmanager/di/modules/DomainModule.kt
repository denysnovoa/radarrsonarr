package com.denysnovoa.nzbmanager.di.modules

import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.GetMovieDetailUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.MoviesApiClient
import com.denysnovoa.nzbmanager.radarr.movie.release.domain.DownloadReleaseUseCase
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.MovieReleaseApiClient
import com.denysnovoa.nzbmanager.radarr.movie.release.view.domain.GetMovieReleaseUseCase
import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.domain.SaveRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.repository.RadarrSettingsRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetLastMoviesUseCase(moviesApiClient: MoviesApiClient) = GetLastMoviesUseCase(moviesApiClient)

    @Provides
    fun provideGetMovieDetailUseCase(moviesApiClient: MoviesApiClient) = GetMovieDetailUseCase(moviesApiClient)

    @Provides
    fun provideGetMovieReleaseUseCase(movieReleaseApiClient: MovieReleaseApiClient) = GetMovieReleaseUseCase(movieReleaseApiClient)

    @Provides
    fun provideDownloadReleaseUseCase(movieReleaseApiClient: MovieReleaseApiClient) = DownloadReleaseUseCase(movieReleaseApiClient)

    @Provides
    fun provideGetRadarrSettingsUseCase(radarrSettingsRepository: RadarrSettingsRepository) = GetRadarrSettingsUseCase(radarrSettingsRepository)

    @Provides
    fun provideSaveRadarrSettingsUseCase(radarrSettingsRepository: RadarrSettingsRepository) = SaveRadarrSettingsUseCase(radarrSettingsRepository)
}
