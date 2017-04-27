package com.denysnovoa.nzbmanager.radarr.movies.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movies.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movies.repository.model.MovieModel
import com.denysnovoa.nzbmanager.radarr.movies.view.MoviesView
import com.denysnovoa.nzbmanager.radarr.movies.view.model.MovieViewModel
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
                        .flatMapIterable { it }
                        .toList()
                        .subscribe(this::moviesOnNext, { moviesView.showErrorLoadMovies() })
        )
    }

    private fun moviesOnNext(moviesModel: List<MovieModel>) {
        moviesView.showMovies(moviesModel.mapNotNull { transform(it) })
    }

    private fun transform(movie: MovieModel) = with(movie) {
        MovieViewModel(id, title)
    }

}
