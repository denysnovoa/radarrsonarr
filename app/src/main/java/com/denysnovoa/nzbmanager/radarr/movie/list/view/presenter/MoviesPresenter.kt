package com.denysnovoa.nzbmanager.radarr.movie.list.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.list.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.api.ApiUnknownHostException
import com.denysnovoa.nzbmanager.radarr.movie.list.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(val view: MoviesView,
                      val errorLog: ErrorLog,
                      val getLastMoviesUseCase: GetLastMoviesUseCase,
                      val moviesViewMapper: MoviesViewMapper,
                      val subscribeOn: Scheduler = Schedulers.io(),
                      val observeOn: Scheduler = AndroidSchedulers.mainThread()) {

    val compositeDisposable = CompositeDisposable()

    fun stop() {
        compositeDisposable.clear()
    }

    fun onResume() {
        view.showLoading()

        compositeDisposable.add(
                getLastMoviesUseCase.get()
                        .subscribeOn(subscribeOn)
                        .observeOn(observeOn)
                        .doOnError { errorLog.log(it) }
                        .doOnNext { view.hideLoading() }
                        .flatMapIterable { it }
                        .toList()
                        .subscribe(
                                {
                                    moviesModel ->
                                    view.showMovies(moviesModel.mapNotNull { moviesViewMapper.transform(it) })
                                },
                                {
                                    error ->
                                    if (error is ApiUnknownHostException) {
                                        view.showConfigureApi()
                                    }
                                    view.showErrorLoadMovies()
                                }
                        )
        )
    }

    fun onMovieClicked(movie: MovieViewModel) {
        view.goToMovieDetail(movie.id)
    }

}
