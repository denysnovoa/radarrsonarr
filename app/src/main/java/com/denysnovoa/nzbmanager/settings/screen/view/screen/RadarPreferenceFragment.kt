package com.denysnovoa.nzbmanager.settings.screen.view.screen

import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@android.annotation.TargetApi(android.os.Build.VERSION_CODES.HONEYCOMB)
class RadarPreferenceFragment : PreferenceFragment(), SettingsView, Preference.OnPreferenceChangeListener {

    lateinit var hostAddress: EditTextPreference
    lateinit var hostPort: EditTextPreference
    lateinit var hostApiKey: EditTextPreference
    lateinit var enableSettings: SwitchPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_radarr)
        setHasOptionsMenu(true)

        hostAddress = findPreference("pref_radarr_hots_address") as EditTextPreference
        hostPort = findPreference("pref_radarr_hots_port") as EditTextPreference
        hostApiKey = findPreference("pref_radarr_api_key") as EditTextPreference
        enableSettings = findPreference("switch_enable_radarr") as SwitchPreference

        hostAddress.onPreferenceChangeListener = this
        hostPort.onPreferenceChangeListener = this
        hostApiKey.onPreferenceChangeListener = this
        enableSettings.onPreferenceChangeListener = this
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onOptionsItemSelected(item: android.view.MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            startActivity<SettingsActivity>()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun showErrorLoadSettings() {
        toast(R.id.error_load_radarr_settings)
    }

    override fun showSettings(radarrSettings: RadarrSettingsViewModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    private fun bindPreferenceSummaryToValue(preference: Preference) {
//        // Set the listener to watch for value changes.
//        preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener
//
//        // Trigger the listener immediately with the preference's
//        // current value.
//        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
//                PreferenceManager
//                        .getDefaultSharedPreferences(preference.context)
//                        .getString(preference.key, ""))
//    }

//                : Boolean {
//            val id = item.itemId
//            if (id == android.R.id.home) {
//                startActivity(android.content.Intent(activity, SettingsActivity::class.java))
//                return true
//            }
//            return super.onOptionsItemSelected(item)
//        }
}