package com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieDetailView
import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.DeleteMovieUseCase
import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.GetMovieDetailUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailPresenter(val view: MovieDetailView,
                           val errorLog: ErrorLog,
                           val getMovieDetailUseCase: GetMovieDetailUseCase,
                           val moviesViewMapper: MoviesViewMapper,
                           val deleteMovieUseCase: DeleteMovieUseCase,
                           val subscribeOn: Scheduler = Schedulers.io(),
                           val observerOn: Scheduler = AndroidSchedulers.mainThread()) {

    val compositeDisposable = CompositeDisposable()

    fun onStop() {
        compositeDisposable.clear()
    }

    fun onResume(id: Int) {
        compositeDisposable.add(
                getMovieDetailUseCase.get(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(errorLog::log)
                        .subscribe(
                                { movie -> view.showDetail(moviesViewMapper.transform(movie)) },
                                { view.showErrorLoadMovie() }
                        )
        )
    }

    fun onDeleteMovie(movieId: Int, deleteFile: Boolean, excludeFromImports: Boolean) {
        compositeDisposable.add(
                deleteMovieUseCase.delete(movieId, deleteFile, excludeFromImports)
                        .subscribeOn(subscribeOn)
                        .observeOn(observerOn)
                        .doOnError(errorLog::log)
                        .subscribe({ view.returnToMoviesView() }, { view.showErrorDeleteMovie() }
                        )

        )
    }
}
