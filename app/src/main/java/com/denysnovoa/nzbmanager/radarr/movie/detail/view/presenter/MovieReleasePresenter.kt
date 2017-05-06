package com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieReleaseView
import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.GetMovieReleaseUseCase
import io.reactivex.disposables.CompositeDisposable

class MovieReleasePresenter(val view: MovieReleaseView,
                            val errorLog: ErrorLog,
                            val getMovieReleaseUseCase: GetMovieReleaseUseCase) {

    val compositeDisposable = CompositeDisposable()


    fun onStop() {
        compositeDisposable.clear()
    }

    fun onResume(){

        getMovieReleaseUseCase.get()

    }
}