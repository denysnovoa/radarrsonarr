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
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun provideMoviesMapper(imagesMapper: MovieImageMapper): MoviesMapper = MoviesMapperImpl(imagesMapper)

    @Provides
    fun provideMovieImageMapper(): MovieImageMapper = MovieImageMapperImpl()

    @Provides
    fun providerMoviesViewMapper(imageViewMapper: MovieImageViewMapper): MoviesViewMapper = MoviesViewMapperImpl(imageViewMapper)

    @Provides
    fun providerMovieImageViewMapper(): MovieImageViewMapper = MovieImageViewMapperImpl()

    @Provides
    fun provideErrorLog() = ErrorLog()
}
