package com.denysnovoa.nzbmanager.radarr.movie.release.domain

import com.denysnovoa.nzbmanager.radarr.movie.release.repository.api.MovieReleaseApiClient
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel

class DownloadReleaseUseCase(val movieReleaseApiClient: MovieReleaseApiClient) {
    fun download(release: MovieReleaseModel)
            = movieReleaseApiClient.download(release)

}
