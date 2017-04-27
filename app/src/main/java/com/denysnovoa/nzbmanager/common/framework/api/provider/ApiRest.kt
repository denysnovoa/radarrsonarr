package com.denysnovoa.nzbmanager.common.framework.api.provider

interface ApiRest {
    fun <T> get(service: Class<T>): T
}