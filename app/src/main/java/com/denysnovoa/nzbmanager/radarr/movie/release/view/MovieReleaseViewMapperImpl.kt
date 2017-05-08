package com.denysnovoa.nzbmanager.radarr.movie.release.view

import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.model.MovieReleaseModel
import com.denysnovoa.nzbmanager.radarr.movie.detail.view.model.MovieReleaseViewModel

class MovieReleaseViewMapperImpl : MovieReleaseViewMapper {

    override fun transform(movieRelease: List<MovieReleaseModel>) = movieRelease.mapNotNull { transform(it) }

    override fun transform(movieRelease: MovieReleaseModel) = with(movieRelease) {
        MovieReleaseViewModel(title,
                size,
                indexerId,
                indexer,
                rejected,
                downloadAllowed,
                age,
                seeders,
                leechers)
    }
}