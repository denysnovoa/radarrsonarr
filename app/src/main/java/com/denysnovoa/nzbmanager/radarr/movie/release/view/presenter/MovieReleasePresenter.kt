package com.denysnovoa.nzbmanager.radarr.movie.release.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.release.view.MovieReleaseView
import com.denysnovoa.nzbmanager.radarr.movie.release.view.domain.GetMovieReleaseUseCase
import com.denysnovoa.nzbmanager.radarr.movie.release.view.mapper.MovieReleaseViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel
import io.reactivex.disposables.CompositeDisposable

class MovieReleasePresenter(val view: MovieReleaseView,
                            val errorLog: ErrorLog,
                            val getMovieReleaseUseCase: GetMovieReleaseUseCase,
                            val movieReleaseViewMapper: MovieReleaseViewMapper
) {

    val compositeDisposable = CompositeDisposable()

    fun onStop() {
        compositeDisposable.clear()
    }

    fun onResume(id: Int) {
//        compositeDisposable.add(
//                getMovieReleaseUseCase
//                        .get(id)
//                        .doOnError { errorLog::log }
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                { movieReleases -> view.showMovieReleases(movieReleaseViewMapper.transform(movieReleases)) },
//                                { view.showErrorSearchReleases() }
//                        )
//        )
    }

    fun onReleaseClicked(releaseViewModel: MovieReleaseViewModel) {


    }
}