package com.sample.mvvmmovies

import com.sample.mvvmmovies.models.moviedb.DiscoverMovieResponse
import com.sample.mvvmmovies.models.moviedb.Result
import com.sample.mvvmmovies.repository.MoviesRepository
import retrofit2.Response

class FakeRepository : MoviesRepository {

    private lateinit var mainList: Response<DiscoverMovieResponse>

    override suspend fun getDiscoverMovie(pageNumber: Int): Response<DiscoverMovieResponse> = mainList

    override suspend fun searchMovies(
        searchQuery: String,
        pageNumber: Int
    ): Response<DiscoverMovieResponse> {
        TODO("Not yet implemented")
    }

    fun mockResponse() {
        val tempList =
            DiscoverMovieResponse(
                1,
                mutableListOf(
                    Result(
                        1, false, "/sss", emptyList(), "en", "Wonder Women",
                        "", 7.0, "/www", "", "Wonder Women", false,
                        7f, ""
                    ), Result(
                        2, false, "/sss", emptyList(), "en", "Batman",
                        "", 7.0, "/www", "", "Batman", false,
                        9f, ""
                    )
                ), 1, 1
            )

        mainList = Response.success(tempList)
    }

    override suspend fun upsert(article: Result): Long {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(article: Result) {
        TODO("Not yet implemented")
    }
}