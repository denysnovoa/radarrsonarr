package com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieDetailView
import com.denysnovoa.nzbmanager.radarr.movie.detail.domain.GetMovieDetailUseCase
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapper
import io.reactivex.disposables.CompositeDisposable

class MovieDetailPresenter(val id: Int,
                           val view: MovieDetailView,
                           val errorLog: ErrorLog,
                           val getMovieDetailUseCase: GetMovieDetailUseCase,
                           val moviesMapper: MoviesMapper) {


    val compositeDisposable = CompositeDisposable()


    fun onStop() {
        compositeDisposable.clear()
    }


    fun onResume() {


    }

}
