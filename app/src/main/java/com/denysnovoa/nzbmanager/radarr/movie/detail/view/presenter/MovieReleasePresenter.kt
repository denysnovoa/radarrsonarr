package com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieReleaseView
import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.GetMovieReleaseUseCase
import com.denysnovoa.nzbmanager.radarr.movie.release.view.MovieReleaseViewMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieReleasePresenter(val view: MovieReleaseView,
                            val errorLog: ErrorLog,
                            val getMovieReleaseUseCase: GetMovieReleaseUseCase,
                            val movieReleaseViewMapper: MovieReleaseViewMapper) {

    val compositeDisposable = CompositeDisposable()

    fun onStop() {
        compositeDisposable.clear()
    }

    fun onResume(id: Int) {
        compositeDisposable.add(
                getMovieReleaseUseCase
                        .get(id)
                        .doOnError { errorLog::log }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { movieReleases -> view.showMovieReleases(movieReleaseViewMapper.transform(movieReleases)) },
                                { view.showErrorSearchReleases() }
                        )
        )
    }
}