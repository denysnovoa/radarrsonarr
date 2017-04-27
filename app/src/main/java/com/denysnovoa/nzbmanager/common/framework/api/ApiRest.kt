package com.denysnovoa.nzbmanager.common.framework.api

interface ApiRest {
    fun <T> get(service: Class<T>): T
}