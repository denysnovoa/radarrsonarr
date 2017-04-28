package com.denysnovoa.nzbmanager.common.framework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.denysnovoa.nzbmanager.BuildConfig
import com.denysnovoa.nzbmanager.common.framework.api.AuthenticationInterceptor
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun Context.toast(text: CharSequence, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadUrl(url: String) {
    val client = OkHttpClient().newBuilder()
            .addInterceptor(AuthenticationInterceptor(ApiKey))
            .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    }
            ).build()

    Picasso.Builder(context)
            .downloader(OkHttp3Downloader(client))
            .build()
            .load(url)
            .resize(166, 250)
            .centerInside()
            .into(this)

}