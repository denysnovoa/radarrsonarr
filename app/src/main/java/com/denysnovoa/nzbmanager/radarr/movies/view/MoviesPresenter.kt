package com.denysnovoa.nzbmanager.radarr.movies.view

import com.denysnovoa.nzbmanager.radarr.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movies.domain.GetLastMoviesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(val moviesView: MoviesView, val errorLog: ErrorLog,
                      val getLastMoviesUseCase: GetLastMoviesUseCase) {

    val compositeDisposable = CompositeDisposable()

    fun stop() {
        compositeDisposable.clear()
    }

    fun getLastMovies() {
        compositeDisposable.add(
                getLastMoviesUseCase.get()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { errorLog.log(it) }
                        .subscribe({ moviesView.showMovies(it) }, { moviesView.showErrorLoadMovies() })
        )
    }
}
