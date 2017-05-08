package com.denysnovoa.nzbmanager.common.framework

import com.google.firebase.crash.FirebaseCrash
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

class ErrorLog : AnkoLogger {
    fun log(error: Throwable) {
        this.error { error }
        FirebaseCrash.report(error)
    }
}
