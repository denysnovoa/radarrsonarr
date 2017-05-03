package com.denysnovoa.nzbmanager.di.modules

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.denysnovoa.nzbmanager.BuildConfig
import com.denysnovoa.nzbmanager.common.framework.api.AuthenticationInterceptor
import com.denysnovoa.nzbmanager.di.qualifier.ApplicationQualifier
import com.denysnovoa.nzbmanager.di.scope.ActivityScope
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
abstract class ActivityModule(protected val activity: AppCompatActivity) {

    @Provides @ActivityScope
    fun provideActivity(): AppCompatActivity = activity

    @Provides @ActivityScope
    fun provideActivityContext(): Context = activity.baseContext

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
