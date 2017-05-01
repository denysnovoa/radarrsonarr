package com.denysnovoa.nzbmanager.common.framework

import android.util.Log
import com.google.firebase.crash.FirebaseCrash

class ErrorLog {
    fun log(error: Throwable?) {
        Log.e("ErrorLog", "Error", error)
        FirebaseCrash.report(error)
    }
}
