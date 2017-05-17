package com.denysnovoa.nzbmanager.settings.screen.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.mapper.RadarrSettingsViewMapper
import com.denysnovoa.nzbmanager.settings.screen.view.model.RadarrSettingsViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RadarrSettingsPresenter(val view: SettingsView,
                              val getRadarrSettingsUseCase: GetRadarrSettingsUseCase,
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
                        .doOnError { errorLog::log }
                        .subscribe(
                                {
                                    radarrSettings ->
                                    this.radarrSettings = radarrSettingsViewMapper.transform(radarrSettings)
                                    view.showSettings(this.radarrSettings)
                                },
                                {
                                    view.showErrorLoadSettings()
                                })
        )
    }

    fun onStop() {
        compositeDisposable.clear()
    }

    fun onHostChange(host: String) {
        radarrSettings.hostName = host
    }

    fun onPortChange(port: Int) {
        radarrSettings.port = port
    }

    fun onApiKeyChange(apiKey: String) {
        radarrSettings.apiKey = apiKey
    }

}