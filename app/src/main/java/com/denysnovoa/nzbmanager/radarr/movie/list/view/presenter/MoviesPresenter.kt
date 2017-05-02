package com.denysnovoa.nzbmanager.radarr.movie.list.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.list.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.model.MovieModel
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(val moviesView: MoviesView,
                      val errorLog: ErrorLog,
                      val getLastMoviesUseCase: GetLastMoviesUseCase,
                      val moviesViewMapper: MoviesViewMapper) {

    val compositeDisposable = CompositeDisposable()

    fun stop() {
        compositeDisposable.clear()
    }

    fun onResume() {

        moviesView.showLoading()

        compositeDisposable.add(
                getLastMoviesUseCase.get()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { errorLog.log(it) }
                        .flatMapIterable { it }
                        .toList()
                        .subscribe(this::moviesOnNext, {
                            moviesView.hideLoading()
                            moviesView.showErrorLoadMovies()
                        })
        )
    }

    private fun moviesOnNext(moviesModel: List<MovieModel>) {
        moviesView.showMovies(moviesModel.mapNotNull { moviesViewMapper.transform(it) })
        moviesView.hideLoading()
    }

}
