package com.denysnovoa.nzbmanager.radarr.movie.release.view.mapper

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel
import java.lang.Math.rint

class MovieReleaseViewMapperImpl : MovieReleaseViewMapper {

    override fun transform(movieRelease: List<MovieReleaseModel>) = movieRelease.mapNotNull { transform(it) }

    override fun transform(movieRelease: MovieReleaseModel) = with(movieRelease) {
        MovieReleaseViewModel(title,
                convertByteToGigabyte(size),
                indexerId,
                indexer,
                rejected,
                downloadAllowed,
                age,
                seeders,
                leechers,
                guid,
                downloadUrl,
                infoUrl)
    }

    fun convertByteToGigabyte(size: Double): Double {
        if (size == 0.toDouble())
            return 0.toDouble()

        return rint(size.div(1048576.0).div(1024) * 100) / 100
    }
}