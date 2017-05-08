package com.denysnovoa.nzbmanager.radarr.movie.detail.repository.mapper

import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.MovieReleaseEntity
import com.denysnovoa.nzbmanager.radarr.movie.detail.repository.model.MovieReleaseModel

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
                leechers)
    }


}