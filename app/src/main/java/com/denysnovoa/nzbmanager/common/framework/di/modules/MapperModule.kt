package com.denysnovoa.nzbmanager.common.framework.di.modules

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MovieImageMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MovieImageMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapperImpl
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun provideMoviesMapper(imagesMapper: MovieImageMapper) = MoviesMapperImpl(imagesMapper)

    @Provides
    fun provideMovieImageMapper() = MovieImageMapperImpl()
}
