package com.denysnovoa.nzbmanager.settings.screen.repository.model

import android.content.Context
import android.content.SharedPreferences
import com.denysnovoa.nzbmanager.R
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceStorageProvider<T>(val context: Context, val name: String, val value: T)
    : ReadWriteProperty<Any?, T> {

    val preferences: SharedPreferences by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = with(preferences.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException(context.getString(R.string.error_save_type_sharedPreference))
        }.apply()
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>) = with(preferences) {
        val res: Any = when (value) {
            is Long -> getLong(name, value)
            is String -> getString(name, value)
            is Int -> getInt(name, value)
            is Boolean -> getBoolean(name, value)
            is Float -> getFloat(name, value)
            else -> throw IllegalArgumentException(context.getString(R.string.error_save_type_sharedPreference))
        }
        res as T
    }
}
