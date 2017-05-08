package com.denysnovoa.nzbmanager.di.modules

import com.denysnovoa.nzbmanager.common.framework.ErrorLog
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MovieImageMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MovieImageMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MovieImageViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MovieImageViewMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.mapper.MovieReleaseMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.repository.mapper.MovieReleaseMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.release.view.mapper.MovieReleaseViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.release.view.mapper.MovieReleaseViewMapperImpl
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun provideMoviesMapper(imagesMapper: MovieImageMapper): MoviesMapper = MoviesMapperImpl(imagesMapper)

    @Provides
    fun provideMovieImageMapper(): MovieImageMapper = MovieImageMapperImpl()

    @Provides
    fun provideMoviesViewMapper(imageViewMapper: MovieImageViewMapper): MoviesViewMapper = MoviesViewMapperImpl(imageViewMapper)

    @Provides
    fun provideMovieImageViewMapper(): MovieImageViewMapper = MovieImageViewMapperImpl()

    @Provides
    fun provideErrorLog() = ErrorLog()

    @Provides
    fun provideMovieReleaseViewMapper(): MovieReleaseViewMapper = MovieReleaseViewMapperImpl()

    @Provides
    fun provideMoviesReleaseMapper(): MovieReleaseMapper = MovieReleaseMapperImpl()
}
