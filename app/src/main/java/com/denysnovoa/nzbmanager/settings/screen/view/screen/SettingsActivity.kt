package com.denysnovoa.nzbmanager.settings.screen.view.screen

import android.preference.PreferenceActivity

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

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setTitle(com.denysnovoa.nzbmanager.R.string.action_settings)
    }

    /** {@inheritDoc}  */
    override fun onIsMultiPane(): Boolean {
        return isXLargeTablet(this)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    /** {@inheritDoc}  */
    @android.annotation.TargetApi(android.os.Build.VERSION_CODES.HONEYCOMB)
    override fun onBuildHeaders(target: List<android.preference.PreferenceActivity.Header>) {
        loadHeadersFromResource(com.denysnovoa.nzbmanager.R.xml.pref_headers, target)
    }

    override fun isValidFragment(fragmentName: String): Boolean {
        return android.preference.PreferenceFragment::class.java.name == fragmentName
                || com.denysnovoa.nzbmanager.settings.screen.view.screen.SettingsActivity.RadarPreferenceFragment::class.java.name == fragmentName
    }

    @android.annotation.TargetApi(android.os.Build.VERSION_CODES.HONEYCOMB)
    class RadarPreferenceFragment : android.preference.PreferenceFragment() {
        override fun onCreate(savedInstanceState: android.os.Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(com.denysnovoa.nzbmanager.R.xml.pref_radarr)
            setHasOptionsMenu(true)

            //bindPreferenceSummaryToValue(findPreference("pref_radarr_hots_address"))
            //bindPreferenceSummaryToValue(findPreference("pref_radarr_hots_port"))
            //bindPreferenceSummaryToValue(findPreference("pref_radarr_api_key"))
        }

        override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
            val id = item.itemId
            if (id == android.R.id.home) {
                startActivity(android.content.Intent(activity, SettingsActivity::class.java))
                return true
            }
            return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private fun isXLargeTablet(context: android.content.Context): Boolean {
        return context.resources.configuration.screenLayout and android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK >= android.content.res.Configuration.SCREENLAYOUT_SIZE_XLARGE
    }

}
