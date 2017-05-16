package com.denysnovoa.nzbmanager.settings.screen.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.settings.screen.domain.GetRadarrSettingsUseCase
import com.denysnovoa.nzbmanager.settings.screen.view.SettingsView
import com.denysnovoa.nzbmanager.settings.screen.view.mapper.RadarrSettingsViewMapper
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

    fun onResume() {
        compositeDisposable.add(
                getRadarrSettingsUseCase.get()
                        .subscribeOn(subscribeOn)
                        .observeOn(observeOn)
                        .doOnError { errorLog::log }
                        .subscribe(
                                {
                                    radarrSettings ->
                                    view.showSettings(radarrSettingsViewMapper.transform(radarrSettings))
                                },
                                {
                                    view.showErrorLoadSettings()
                                })
        )
    }

    fun onStop() {
        compositeDisposable.clear()
    }

    fun onHostChange(newValue: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onPortChange(newValue: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onApiKeyChange(newValue: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}