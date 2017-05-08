package com.denysnovoa.nzbmanager.radarr.movie.release.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.MovieReleaseEntity
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel

class MovieReleaseMapperImpl : MovieReleaseMapper {
    override fun transform(movie: MovieReleaseEntity) = with(movie) {
        MovieReleaseModel(title,
                size,
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

    override fun transform(movie: MovieReleaseModel) = with(movie) {
        MovieReleaseEntity(title,
                size,
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
}