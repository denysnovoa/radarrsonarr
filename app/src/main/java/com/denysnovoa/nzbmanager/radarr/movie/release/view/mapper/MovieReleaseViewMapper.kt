package com.denysnovoa.nzbmanager.radarr.movie.release.view.mapper

interface MovieReleaseViewMapper {
    fun transform(movieRelease: List<com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel>): List<com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel>
    fun transform(movieRelease: com.denysnovoa.nzbmanager.radarr.movie.release.repository.model.MovieReleaseModel): com.denysnovoa.nzbmanager.radarr.movie.release.view.model.MovieReleaseViewModel
}

