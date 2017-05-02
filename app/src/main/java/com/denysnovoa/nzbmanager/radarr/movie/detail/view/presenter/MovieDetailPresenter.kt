package com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieDetailView
import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.GetMovieDetailUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailPresenter(val id: Int,
                           val view: MovieDetailView,
                           val errorLog: ErrorLog,
                           val getMovieDetailUseCase: GetMovieDetailUseCase,
                           val moviesViewMapper: MoviesViewMapper) {

    val compositeDisposable = CompositeDisposable()

    fun onStop() {
        compositeDisposable.clear()
    }

    fun onResume() {
        compositeDisposable.add(
                getMovieDetailUseCase.get(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { errorLog::log }
                        .subscribe(
                                { movie -> view.showDetail(moviesViewMapper.transform(movie)) },
                                { view.showErrorLoadMovie() }
                        )
        )
    }
}
