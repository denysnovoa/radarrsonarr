package com.denysnovoa.nzbmanager.settings.screen.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.domain.SaveRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.mapper.RadarrSettingsViewMapper
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RadarrSettingsPresenter(val view: SettingsView,
                              val getRadarrSettingsUseCase: GetRadarrSettingsUseCase,
                              val saveRadarrSettingsUseCase: SaveRadarrSettingsUseCase,
                              val radarrSettingsViewMapper: RadarrSettingsViewMapper,
                              val errorLog: ErrorLog,
                              val subscribeOn: Scheduler = Schedulers.io(),
                              val observeOn: Scheduler = AndroidSchedulers.mainThread()) {

    val compositeDisposable = CompositeDisposable()
    lateinit var radarrSettings: RadarrSettingsViewModel

    fun onResume() {
        compositeDisposable.add(
                getRadarrSettingsUseCase.get()
                        .subscribeOn(subscribeOn)
                        .observeOn(observeOn)
                        .doOnError(errorLog::log)
                        .subscribe(
                                {
                                    radarrSettingsModel ->
                                    radarrSettings = radarrSettingsViewMapper.transform(radarrSettingsModel)
                                    view.showSettings(radarrSettings)
                                },
                                {
                                    view.showErrorLoadSettings()
                                })
        )
    }

    fun onStop() {
        compositeDisposable.clear()
    }

    fun onHostChange(host: String): Boolean {
        if (host.isNullOrEmpty()) {
            view.showHostRadarrSettingsIsRequired()
            return false
        }

        radarrSettings.hostName = host
        saveRadarrSettings()
        return true
    }

    fun onPortChange(port: Int): Boolean {
        if (port <= 0) {
            view.showPortRadarrSettingsIsRequired()
            return false
        }

        radarrSettings.port = port
        saveRadarrSettings()
        return true
    }

    fun onApiKeyChange(apiKey: String): Boolean {
        if (apiKey.isNullOrEmpty()) {
            view.showApiKeyRadarrSettingsIsRequired()
            return false
        }

        radarrSettings.apiKey = apiKey
        saveRadarrSettings()
        return true
    }

    private fun saveRadarrSettings() {
        compositeDisposable.add(
                saveRadarrSettingsUseCase.save(radarrSettingsViewMapper.transform(radarrSettings))
                        .subscribeOn(subscribeOn)
                        .observeOn(observeOn)
                        .doOnError(errorLog::log)
                        .subscribe {
                            view.showSettings(radarrSettings)
                        }
        )
    }

}