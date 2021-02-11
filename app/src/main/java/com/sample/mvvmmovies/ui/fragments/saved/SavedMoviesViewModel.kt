package com.sample.mvvmmovies.ui.fragments.saved

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.mvvmmovies.models.moviedb.Result
import com.sample.mvvmmovies.repository.MoviesRepositoryImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

class SavedMoviesViewModel @ViewModelInject constructor(
    @ApplicationContext val context: Context,
    val moviesRepository: MoviesRepositoryImpl
) : ViewModel() {

    fun saveArticle(movies: Result) = viewModelScope.launch {
        moviesRepository.upsert(movies)
    }

    fun getSavedNews() = moviesRepository.getSavedNews()

    fun deleteArticle(movie: Result) = viewModelScope.launch {
        moviesRepository.deleteArticle(movie)
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}












