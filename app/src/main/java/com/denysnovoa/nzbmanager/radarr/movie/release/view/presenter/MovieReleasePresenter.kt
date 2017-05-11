package com.denysnovoa.nzbmanager.radarr.movie.release.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.release.domain.DownloadReleaseUseCase
import com.denysnovoa.nzbmanager.radarr.movie.release.view.MovieReleaseView
import com.denysnovoa.nzbmanager.radarr.movie.release.view.domain.GetMovieReleaseUseCase
import com.denysnovoa.nzbmanager.radarr.movie.release.view.mapper.MovieReleaseViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieReleasePresenter(val view: MovieReleaseView,
                            val errorLog: ErrorLog,
                            val getMovieReleaseUseCase: GetMovieReleaseUseCase,
                            val downloadReleaseUseCase: DownloadReleaseUseCase,
                            val movieReleaseViewMapper: MovieReleaseViewMapper) {

    val compositeDisposable = CompositeDisposable()
    var callApi = false

    fun onStop() {
        compositeDisposable.clear()
    }

    fun onResume(id: Int) {

        view.showLoading()
        if (!callApi) {

            callApi = true
            compositeDisposable.add(
                    getMovieReleaseUseCase
                            .get(id)
                            .doOnError(errorLog::log)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnNext {
                                callApi = false
                                view.hideLoading()
                            }
                            .subscribe(
                                    { movieReleases ->
                                        view.showMovieReleases(movieReleaseViewMapper.transform(movieReleases))
                                    },
                                    {
                                        view.showErrorSearchReleases()
                                    }
                            )
            )
        }
    }

    fun onReleaseClicked(releaseViewModel: MovieReleaseViewModel) {
        compositeDisposable.add(
                downloadReleaseUseCase
                        .download(movieReleaseViewMapper.transform(releaseViewModel))
                        .doOnError(errorLog::log)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ view.showDownloadOk() }, { view.showErrorDownload() })
        )
    }
}