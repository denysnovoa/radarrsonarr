package com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen

import android.os.Bundle
import com.denysnovoa.nzbmanager.common.framework.ui.BaseActivityAnko
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieSearchDownloadView

class MovieSearchDownloadActivity : BaseActivityAnko<MovieSearchDownloadLayout>(), MovieSearchDownloadView {

    override val ui = MovieSearchDownloadLayout()

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
