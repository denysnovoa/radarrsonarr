package com.denysnovoa.nzbmanager.common.framework.api

import android.content.Context
import android.net.ConnectivityManager

class NetworkConnection(context: Context) {
    val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun hasNetwork(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
