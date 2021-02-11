package com.sample.mvvmmovies.ui.fragments.discover

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.mvvmmovies.models.moviedb.DiscoverMovieResponse
import com.sample.mvvmmovies.repository.MoviesRepository
import com.sample.mvvmmovies.repository.MoviesRepositoryImpl
import com.sample.mvvmmovies.util.NetworkManager
import com.sample.mvvmmovies.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class DiscoverMoviesViewModel @ViewModelInject constructor(
    @ApplicationContext val context: Context,
    val moviesRepository: MoviesRepository
) : ViewModel() {

    val discoverMovies: MutableLiveData<Resource<DiscoverMovieResponse>> = MutableLiveData()
    var discoverMoviesPage = 1
    var discoverMovieResponse: DiscoverMovieResponse? = null

    val searchMovies: MutableLiveData<Resource<DiscoverMovieResponse>> = MutableLiveData()
    var searchMoviesPage = 1
    var searchMoviesResponse: DiscoverMovieResponse? = null
    var newSearchQuery: String? = null
    var oldSearchQuery: String? = null

    init {
        discoverMovies()
    }

    fun discoverMovies() = viewModelScope.launch {
        safeDiscoverMoviesCall()
    }

    private fun handleBreakingNewsResponse(response: Response<DiscoverMovieResponse>): Resource<DiscoverMovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                discoverMoviesPage++
                if (discoverMovieResponse == null) {
                    discoverMovieResponse = resultResponse
                } else {
                    val oldArticles = discoverMovieResponse?.results
                    val newArticles = resultResponse.results
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(discoverMovieResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private suspend fun safeDiscoverMoviesCall() {
        discoverMovies.postValue(Resource.Loading())
        try {
            if (NetworkManager.isOnline(context)) {
                val response = moviesRepository.getDiscoverMovie(discoverMoviesPage)
                discoverMovies.postValue(handleBreakingNewsResponse(response))
            } else {
                discoverMovies.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            System.out.println(t.printStackTrace())
            when (t) {
                is IOException -> discoverMovies.postValue(Resource.Error("Network Failure"))
                else -> discoverMovies.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
}












