package com.sample.mvvmmovies.ui.fragments.search

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.mvvmmovies.models.moviedb.DiscoverMovieResponse
import com.sample.mvvmmovies.repository.MoviesRepositoryImpl
import com.sample.mvvmmovies.util.NetworkManager
import com.sample.mvvmmovies.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class SearchMoviesViewModel @ViewModelInject constructor(
    @ApplicationContext val context: Context,
    val moviesRepository: MoviesRepositoryImpl
) : ViewModel() {

    val searchMovies: MutableLiveData<Resource<DiscoverMovieResponse>> = MutableLiveData()
    var searchMoviesPage = 1
    var searchMoviesResponse: DiscoverMovieResponse? = null
    var newSearchQuery: String? = null
    var oldSearchQuery: String? = null

    fun searchMovies(searchQuery: String) = viewModelScope.launch {
        safeSearchMoviesCall(searchQuery)
    }

    private fun handleSearchNewsResponse(response: Response<DiscoverMovieResponse>): Resource<DiscoverMovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (searchMoviesResponse == null || newSearchQuery != oldSearchQuery) {
                    searchMoviesPage = 1
                    oldSearchQuery = newSearchQuery
                    searchMoviesResponse = resultResponse
                } else {
                    searchMoviesPage++
                    val oldArticles = searchMoviesResponse?.results
                    val newArticles = resultResponse.results
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchMoviesResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeSearchMoviesCall(searchQuery: String) {
        newSearchQuery = searchQuery
        searchMovies.postValue(Resource.Loading())
        try {
            if (NetworkManager.hasInternetConnection(context)) {
                val response = moviesRepository.searchMovies(searchQuery, searchMoviesPage)
                searchMovies.postValue(handleSearchNewsResponse(response))
            } else {
                searchMovies.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> searchMovies.postValue(Resource.Error("Network Failure"))
                else -> searchMovies.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
}












