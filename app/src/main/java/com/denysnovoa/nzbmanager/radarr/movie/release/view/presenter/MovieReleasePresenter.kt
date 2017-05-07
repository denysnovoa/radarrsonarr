package com.denysnovoa.nzbmanager.radarr.movie.release.view.presenter

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel
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

        val releases = listOf<MovieReleaseModel>(MovieReleaseModel("uno", 23333333.toDouble(), 1, "Rarbj", true, true, 12, 20, 32),
                MovieReleaseModel("uno222 2222222 2222", 23333333.toDouble(), 1, "Rarbj", true, true, 12, 20, 32),
                MovieReleaseModel("uno", 2333333.toDouble(), 1, "Rarbj", true, true, 12, 20, 32),
                MovieReleaseModel("unos s ss s s ", 13333333.toDouble(), 1, "Rarbj", true, true, 1, 20, 32),
                MovieReleaseModel("uno", 12333333.toDouble(), 1, "Rarbj", false, true, 12, 20, 32),
                MovieReleaseModel("unos s s ssss s sss  s", 23333333.toDouble(), 1, "Rarbj", true, false, 12, 20, 32),
                MovieReleaseModel("uno", 23333333.toDouble(), 1, "Rarbj", true, true, 122, 20, 32),
                MovieReleaseModel("uno", 23333333.toDouble(), 1, "Rarbj", true, true, 12, 20, 32))

        view.showMovieReleases(movieReleaseViewMapper.transform(releases))


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