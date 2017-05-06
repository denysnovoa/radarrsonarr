package com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen

import android.os.Bundle
import android.view.MenuItem
import com.denysnovoa.nzbmanager.common.framework.ui.BaseActivityAnko
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieSearchDownloadView

class MovieReleaseActivity : BaseActivityAnko<MovieReleaseLayout>(), MovieSearchDownloadView {

    override val ui = MovieReleaseLayout()

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeToolbar()
    }

    fun initializeToolbar() {
        setSupportActionBar(ui.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }
}
