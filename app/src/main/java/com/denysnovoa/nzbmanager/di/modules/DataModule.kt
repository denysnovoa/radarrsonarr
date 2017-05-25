package com.denysnovoa.nzbmanager.di.modules

import android.content.Context
import com.denysnovoa.nzbmanager.common.framework.ApiCacheKey
import com.denysnovoa.nzbmanager.common.framework.api.ApiOkHttpClient
import com.denysnovoa.nzbmanager.common.framework.api.AuthenticationInterceptor
import com.denysnovoa.nzbmanager.common.framework.api.NetworkConnection
import com.denysnovoa.nzbmanager.common.framework.api.cache.ApiCacheProvider
import com.denysnovoa.nzbmanager.common.framework.api.cache.NetworkCacheInterceptor
import com.denysnovoa.nzbmanager.common.framework.api.cache.OfflineCacheInterceptor
import com.denysnovoa.nzbmanager.common.framework.api.offline.OfflineJson
import com.denysnovoa.nzbmanager.common.framework.api.offline.OfflineJsonProvider
import com.denysnovoa.nzbmanager.common.framework.api.provider.ApiRestProvider
import com.denysnovoa.nzbmanager.di.qualifier.ApiCacheKey
import com.denysnovoa.nzbmanager.di.qualifier.ApiKey
import com.denysnovoa.nzbmanager.di.qualifier.ApiUrl
import com.denysnovoa.nzbmanager.di.qualifier.ApplicationQualifier
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.RadarrMoviesApiRest
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.RadarrMovieReleaseApiRest
import com.denysnovoa.nzbmanager.settings.screen.repository.RadarrSettingsStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideRadarrMoviesApiRest(apiRestProvider: ApiRestProvider) = apiRestProvider.get(RadarrMoviesApiRest::class.java)

    @Provides @Singleton
    fun provideApiRestProvider(@ApiUrl apiUrl: String, apiOkHttpClient: ApiOkHttpClient) = ApiRestProvider(apiUrl, apiOkHttpClient)

    @Provides
    fun provideRadarrMovieReleaseApiRest(apiRestProvider: ApiRestProvider) = apiRestProvider.get(RadarrMovieReleaseApiRest::class.java)

    @Provides @Singleton
    fun provideApiCacheProvider(@ApplicationQualifier context: Context, @ApiCacheKey apiCacheKey: String) = ApiCacheProvider(context, apiCacheKey)

    @Provides @Singleton
    fun provideApiOkHttpClient(authenticatorInterceptor: AuthenticationInterceptor, networkCacheInterceptor: NetworkCacheInterceptor,
                               offlineCacheInterceptor: OfflineCacheInterceptor, apiCacheProvider: ApiCacheProvider)
            = ApiOkHttpClient(authenticatorInterceptor, networkCacheInterceptor, offlineCacheInterceptor, apiCacheProvider)

    @Provides @Singleton
    fun provideAuthenticationInterceptor(@ApiKey apiKey: String) = AuthenticationInterceptor(apiKey)

    @Provides @Singleton @ApiKey
    fun provideApiKey(@ApplicationQualifier context: Context): String = RadarrSettingsStorage(context).apiKey

    @Provides @Singleton @ApiUrl
    fun provideApiUrl(@ApplicationQualifier context: Context): String =
            "http://${RadarrSettingsStorage(context).apiHost}:${RadarrSettingsStorage(context).apiPort}/"

    @Provides @Singleton @ApiCacheKey
    fun provideApiCacheKey(): String = ApiCacheKey

    @Provides @Singleton
    fun provideNetworkCacheInterceptor() = NetworkCacheInterceptor()

    @Provides @Singleton
    fun provideNetworkConnection(@ApplicationQualifier context: Context) = NetworkConnection(context)

    @Provides @Singleton
    fun provideOfflineCacheInterceptor(networkConnection: NetworkConnection) = OfflineCacheInterceptor(networkConnection)

    @Provides @Singleton
    fun provideOfflineJsonProvider(@ApplicationQualifier context: Context): OfflineJson = OfflineJsonProvider(context)
}

