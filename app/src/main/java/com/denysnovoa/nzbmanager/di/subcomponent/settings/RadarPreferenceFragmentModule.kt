package com.denysnovoa.nzbmanager.di.subcomponent.settings

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.di.modules.FragmentModule
import com.denysnovoa.nzbmanager.di.scope.FragmentScope
import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.domain.SaveRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.mapper.RadarrSettingsViewMapper
import com.denysnovoa.nzbmanager.settings.screen.view.presenter.RadarrSettingsPresenter
import com.denysnovoa.nzbmanager.settings.screen.view.screen.RadarPreferenceFragment
import dagger.Module
import dagger.Provides

@Module
class RadarPreferenceFragmentModule(fragment: RadarPreferenceFragment) : FragmentModule(fragment) {

    @Provides @FragmentScope
    fun provideRadarPreferenceView(): SettingsView = fragment as SettingsView

    @Provides @FragmentScope
    fun provideRadarrSettingsPresenter(view: SettingsView,
                                       getRadarrSettingsUseCase: GetRadarrSettingsUseCase,
                                       saveRadarrSettingsUseCase: SaveRadarrSettingsUseCase,
                                       radarrSettingsViewMapper: RadarrSettingsViewMapper,
                                       errorLog: ErrorLog)
            = RadarrSettingsPresenter(view, getRadarrSettingsUseCase, saveRadarrSettingsUseCase, radarrSettingsViewMapper, errorLog)

}
