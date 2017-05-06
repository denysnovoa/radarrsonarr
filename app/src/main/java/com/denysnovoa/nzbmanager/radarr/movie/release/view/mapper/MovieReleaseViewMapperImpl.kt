package com.denysnovoa.nzbmanager.radarr.movie.release.view.mapper

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel
import com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel

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