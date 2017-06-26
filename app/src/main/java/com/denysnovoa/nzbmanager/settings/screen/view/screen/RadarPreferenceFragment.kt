package com.denysnovoa.nzbmanager.settings.screen.view.screen

import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.Preference
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.BasePreferenceFragment
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.subcomponent.settings.RadarPreferenceFragmentModule
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel
import com.denysnovoa.nzbmanager.settings.screen.view.presenter.RadarrSettingsPresenter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

@android.annotation.TargetApi(android.os.Build.VERSION_CODES.HONEYCOMB)
class RadarPreferenceFragment : BasePreferenceFragment(), SettingsView, Preference.OnPreferenceChangeListener {

    companion object {
        val PREF_RADARR_HOST = "pref_radarr_host"
        val PREF_RADARR_PORT = "pref_radarr_port"
        val PREF_RADARR_API_KEY = "pref_radarr_api_key"
    }

    @Inject
    lateinit var presenter: RadarrSettingsPresenter

    lateinit var hostAddress: EditTextPreference
    lateinit var hostPort: EditTextPreference
    lateinit var hostApiKey: EditTextPreference

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(RadarPreferenceFragmentModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_radarr)
        setHasOptionsMenu(true)

        hostAddress = findPreference(PREF_RADARR_HOST) as EditTextPreference
        hostPort = findPreference(PREF_RADARR_PORT) as EditTextPreference
        hostApiKey = findPreference(PREF_RADARR_API_KEY) as EditTextPreference

        hostAddress.onPreferenceChangeListener = this
        hostPort.onPreferenceChangeListener = this
        hostApiKey.onPreferenceChangeListener = this
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onResume() {
        super.onResume()

        presenter.onResume()
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

    override fun showSettings(radarrSettings: RadarrSettingsViewModel) = with(radarrSettings)
    {
        hostAddress.summary = hostName
        hostApiKey.summary = apiKey
        hostPort.summary = port.toString()
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?) = when (preference?.key) {
        PREF_RADARR_HOST -> {
            presenter.onHostChange(newValue as String)
        }
        PREF_RADARR_PORT -> {
            presenter.onPortChange((newValue as String).toInt())
        }
        PREF_RADARR_API_KEY -> {
            presenter.onApiKeyChange(newValue as String)
            true
        }
        else -> false
    }

    override fun showHostRadarrSettingsIsRequired() {
        toast(R.string.requiered_radarr_host)
    }

    override fun showPortRadarrSettingsIsRequired() {
        toast(R.string.requiered_radarr_port)
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