package com.sample.mvvmmovies.repository

import com.sample.mvvmmovies.api.MoviesAPI
import com.sample.mvvmmovies.db.MoviesDatabase
import com.sample.mvvmmovies.models.moviedb.Result
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    val api : MoviesAPI,
    val db: MoviesDatabase
) : MoviesRepository {

    override suspend fun getDiscoverMovie(pageNumber: Int) =
        api.getDiscoverMovie(pageNumber)

    override suspend fun searchMovies(searchQuery: String, pageNumber: Int) =
        api.searchMovie(searchQuery, pageNumber)

    override suspend fun upsert(article: Result) = db.getMoviesDao().upsert(article)

    fun getSavedNews() = db.getMoviesDao().getAllMovies()

    override suspend fun deleteArticle(article: Result) = db.getMoviesDao().deleteArticle(article)
}