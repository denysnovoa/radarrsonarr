package com.denysnovoa.nzbmanager.common.framework

import android.util.Log

class ErrorLog {
    fun log(error: Throwable?) {
        Log.e("ErrorLog", "Error", error)
    }
}
