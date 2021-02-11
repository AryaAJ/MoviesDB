package com.sample.mvvmmovies.models.moviedb

data class DiscoverMovieResponse(
    val page: Int,
    val results: MutableList<Result>,
    val total_pages: Int,
    val total_results: Int
)