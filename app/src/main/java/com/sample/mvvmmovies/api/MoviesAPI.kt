package com.sample.mvvmmovies.api

import com.sample.mvvmmovies.models.moviedb.DiscoverMovieResponse
import com.sample.mvvmmovies.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun getDiscoverMovie(@Query("page") page: Int, @Query("api_key") api: String = API_KEY): Response<DiscoverMovieResponse>

    @GET("/3/search/movie?language=en")
    suspend fun searchMovie(@Query("query") query: String, @Query("page") page: Int, @Query("api_key") api: String = API_KEY): Response<DiscoverMovieResponse>

}