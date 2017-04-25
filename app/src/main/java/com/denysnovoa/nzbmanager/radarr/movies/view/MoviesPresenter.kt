package com.denysnovoa.nzbmanager.radarr.movies.view

import com.denysnovoa.nzbmanager.radarr.movies.domain.GetLastMoviesUseCase
import com.denysnovoa.nzbmanager.radarr.movies.domain.modelView.MoviesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(val getLastMoviesUseCase: GetLastMoviesUseCase) {

    val compositeDisposable = CompositeDisposable()

    fun stop() {
        compositeDisposable.clear()
    }

    fun getLastMovies() {
        compositeDisposable.add(
                getLastMoviesUseCase.get()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { managementError(it) }
                        .subscribe({ showMovies(it) }, { managementError(it) })
        )
    }

    private fun managementError(error: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showMovies(movies: List<MoviesView>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
