package com.denysnovoa.nzbmanager.di.subcomponent.movies

import android.content.Context
import com.denysnovoa.nzbmanager.BuildConfig
import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.common.framework.api.AuthenticationInterceptor
import com.denysnovoa.nzbmanager.di.modules.ActivityModule
import com.denysnovoa.nzbmanager.di.qualifier.ApplicationQualifier
import com.denysnovoa.nzbmanager.di.scope.ActivityScope
import com.denysnovoa.nzbmanager.radarr.movie.list.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.view.presenter.MoviesPresenter
import com.denysnovoa.nzbmanager.radarr.movie.list.view.screen.MoviesActivity
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class MoviesActivityModule(activity: MoviesActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideMoviesView(): MoviesView = activity as MoviesView

    @Provides @ActivityScope
    fun provideMoviesPresenter(view: MoviesView,
                               errorLog: ErrorLog,
                               getLastMoviesUseCase: GetLastMoviesUseCase,
                               moviesViewMapper: MoviesViewMapper)
            = MoviesPresenter(view, errorLog, getLastMoviesUseCase, moviesViewMapper)

    @Provides @ActivityScope
    fun providePicasso(@ApplicationQualifier context: Context, authenticationInterceptor: AuthenticationInterceptor): Picasso {
        val client = OkHttpClient().newBuilder()
                .cache(OkHttp3Downloader.createDefaultCache(context))
                .addInterceptor(authenticationInterceptor)
                .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                        }
                ).build()

        return Picasso.Builder(context)
                .downloader(OkHttp3Downloader(client))
                .build()
    }
}
