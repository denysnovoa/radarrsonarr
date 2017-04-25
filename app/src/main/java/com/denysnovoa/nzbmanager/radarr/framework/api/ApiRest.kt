package com.denysnovoa.nzbmanager.radarr.framework.api

interface ApiRest {
    fun <T> get(service: Class<T>): T
}