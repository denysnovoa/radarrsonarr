package com.denysnovoa.nzbmanager.settings.screen.view.screen

import android.os.Bundle
import android.preference.PreferenceFragment
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel
import org.jetbrains.anko.startActivity

@android.annotation.TargetApi(android.os.Build.VERSION_CODES.HONEYCOMB)
class RadarPreferenceFragment : PreferenceFragment(), SettingsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_radarr)
        setHasOptionsMenu(true)

        //bindPreferenceSummaryToValue(findPreference("pref_radarr_hots_address"))
        //bindPreferenceSummaryToValue(findPreference("pref_radarr_hots_port"))
        //bindPreferenceSummaryToValue(findPreference("pref_radarr_api_key"))
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onOptionsItemSelected(item: android.view.MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            startActivity<SettingsActivity>(); true
        }
        else -> super.onOptionsItemSelected(item)

    }

    override fun showErrorLoadSettings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSettings(radarrSettings: RadarrSettingsViewModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//                : Boolean {
//            val id = item.itemId
//            if (id == android.R.id.home) {
//                startActivity(android.content.Intent(activity, SettingsActivity::class.java))
//                return true
//            }
//            return super.onOptionsItemSelected(item)
//        }
}