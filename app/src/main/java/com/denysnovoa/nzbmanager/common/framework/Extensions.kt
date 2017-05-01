package com.denysnovoa.nzbmanager.common.framework

import android.app.Activity
import android.content.Context
import android.content.Intent
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
            .cache(OkHttp3Downloader.createDefaultCache(context))
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
            .centerCrop()
            .fit()
            .into(this)

}

inline fun <reified T : View> Activity.findView(id: Int) = findViewById(id) as T

inline fun <reified T : Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}
