package com.denysnovoa.nzbmanager.settings.screen

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.view.MenuItem
import com.denysnovoa.nzbmanager.R

/**
 * A [PreferenceActivity] that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 *
 *
 * See [
   * Android Design: Settings](http://developer.android.com/design/patterns/settings.html) for design guidelines and the [Settings
   * API Guide](http://developer.android.com/guide/topics/ui/settings.html) for more information on developing a Settings UI.
 */
class SettingsActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setTitle(R.string.action_settings)
    }

    /** {@inheritDoc}  */
    override fun onIsMultiPane(): Boolean {
        return isXLargeTablet(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    /** {@inheritDoc}  */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onBuildHeaders(target: List<PreferenceActivity.Header>) {
        loadHeadersFromResource(R.xml.pref_headers, target)
    }

    override fun isValidFragment(fragmentName: String): Boolean {
        return PreferenceFragment::class.java.name == fragmentName
                || RadarPreferenceFragment::class.java.name == fragmentName
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class RadarPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_radarr)
            setHasOptionsMenu(true)

            //bindPreferenceSummaryToValue(findPreference("pref_radarr_hots_address"))
            //bindPreferenceSummaryToValue(findPreference("pref_radarr_hots_port"))
            //bindPreferenceSummaryToValue(findPreference("pref_radarr_api_key"))
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            if (id == android.R.id.home) {
                startActivity(Intent(activity, SettingsActivity::class.java))
                return true
            }
            return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private fun isXLargeTablet(context: Context): Boolean {
        return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_XLARGE
    }

}
