package com.denysnovoa.nzbmanager.radarr.movie.detail.view.screen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.ui.BaseActivity
import com.denysnovoa.nzbmanager.di.ApplicationComponent
import com.denysnovoa.nzbmanager.di.subcomponent.movieDetail.MovieDetailActivityModule
import com.denysnovoa.nzbmanager.radarr.movie.detail.MovieDetailView
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.presenter.MovieDetailPresenter
import com.denysnovoa.nzbmanager.radarr.movie.list.view.model.MovieViewModel
import com.denysnovoa.nzbmanager.radarr.movie.release.view.screen.MovieReleaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*
import org.jetbrains.anko.*
import javax.inject.Inject


class MovieDetailActivity : BaseActivity(), MovieDetailView {

    companion object {
        val PARAMETER_MOVIE_ID: String = "PARAMETER_MOVIE_ID"
    }

    @Inject
    lateinit var presenter: MovieDetailPresenter

    @Inject
    lateinit var picasso: Picasso

    var movieId = 0

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(MovieDetailActivityModule(this))
                .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        initializeToolbar()

        movieId = intent.extras.getInt(PARAMETER_MOVIE_ID)

        fab.setOnClickListener({ view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbar_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(movieId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_movie_search_download -> {
            startActivity<MovieReleaseActivity>(PARAMETER_MOVIE_ID to movieId)
            true
        }
        R.id.action_delete_movie -> {
            showAlertToConfirmDeleteMovie()
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }

    override fun showDetail(movie: MovieViewModel) {
        with(movie) {
            toolbar_layout_movie.title = title
            movie_status.text = when (status) {
                "inCinemas" -> "in Cinemas"
                "released" -> "released"
                "announced" -> " announced"
                else -> "default status"
            }
            movie_overview.text = overview
            movie_downloaded.text = when (downloaded) {
                true -> getString(R.string.literal_download_movie).toUpperCase()
                else -> getString(R.string.literal_no_download_movie).toUpperCase()
            }
            movie_monitored.text = when (monitored) {
                true -> getString(R.string.literal_monitored_movie).toUpperCase()
                else -> getString(R.string.literal_no_monitored_movie).toUpperCase()
            }
            picasso.load(imageBanner)
                    .centerCrop()
                    .fit()
                    .into(image_toolbar)
        }

    }

    override fun showErrorLoadMovie() {
        toast(getString(R.string.error_load_movie))
    }

    override fun returnToMoviesView() {
        onBackPressed()
    }

    override fun showErrorDeleteMovie() {
        toast(getString(R.string.error_delete_muvie))
    }

    fun showAlertToConfirmDeleteMovie() {
        alert {
            customView {
                verticalLayout {
                    padding = dip(32)
                    val checkDeleteFiles = switch {
                        text = getString(R.string.chek_delete_all_files)
                        textSize = 16f
                        padding = dip(8)
                    }
                    val checkExcludeImports = switch {
                        text = getString(R.string.check_excludo_auto_import)
                        textSize = 16f
                        padding = dip(8)
                    }
                    positiveButton(getString(R.string.yes)) {
                        presenter.onDeleteMovie(movieId, checkDeleteFiles.isChecked, checkExcludeImports.isChecked)
                    }
                    negativeButton(getString(R.string.no))
                }
            }
        }.show()
    }
}
