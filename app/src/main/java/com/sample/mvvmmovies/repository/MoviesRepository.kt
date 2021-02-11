package com.sample.mvvmmovies.repository

import com.sample.mvvmmovies.models.moviedb.DiscoverMovieResponse
import com.sample.mvvmmovies.models.moviedb.Result
import retrofit2.Response

interface MoviesRepository {

    suspend fun getDiscoverMovie(pageNumber: Int) : Response<DiscoverMovieResponse>

    suspend fun searchMovies(searchQuery: String, pageNumber: Int): Response<DiscoverMovieResponse>

    suspend fun upsert(article: Result): Long

    suspend fun deleteArticle(article: Result)
}