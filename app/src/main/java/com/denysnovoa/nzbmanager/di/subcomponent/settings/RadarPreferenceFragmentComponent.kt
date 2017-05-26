package com.denysnovoa.nzbmanager.di.subcomponent.settings

import com.denysnovoa.nzbmanager.di.scope.FragmentScope
import com.denysnovoa.nzbmanager.settings.screen.view.screen.RadarPreferenceFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = arrayOf(RadarPreferenceFragmentModule::class))
interface RadarPreferenceFragmentComponent {
    fun injectTo(fragment: RadarPreferenceFragment)
}
