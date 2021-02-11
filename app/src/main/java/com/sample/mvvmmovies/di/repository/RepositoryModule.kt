package com.sample.mvvmmovies.di.repository

import com.sample.mvvmmovies.api.MoviesAPI
import com.sample.mvvmmovies.db.MoviesDatabase
import com.sample.mvvmmovies.repository.MoviesRepository
import com.sample.mvvmmovies.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(api: MoviesAPI, db: MoviesDatabase): MoviesRepository =
        MoviesRepositoryImpl(api, db)
}