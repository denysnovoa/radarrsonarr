package com.denysnovoa.nzbmanager.common.framework.di.modules

import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MovieImageMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MovieImageMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.list.repository.mapper.MoviesMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MovieImageViewMapper
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MovieImageViewMapperImpl
import com.denysnovoa.nzbmanager.radarr.movie.list.view.mapper.MoviesViewMapperImpl
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun provideMoviesMapper(imagesMapper: MovieImageMapper) = MoviesMapperImpl(imagesMapper)

    @Provides
    fun provideMovieImageMapper() = MovieImageMapperImpl()

    @Provides
    fun providerMoviesViewMapper(imageViewMapper: MovieImageViewMapper) = MoviesViewMapperImpl(imageViewMapper)

    @Provides
    fun providerMoviesImageViewMapper() = MovieImageViewMapperImpl()
}
