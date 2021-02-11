package com.sample.mvvmmovies.ui.fragments.detail

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.mvvmmovies.models.moviedb.Result
import com.sample.mvvmmovies.repository.MoviesRepositoryImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    @ApplicationContext val context: Context,
    val moviesRepository: MoviesRepositoryImpl
) : ViewModel() {

    fun saveArticle(movies: Result) = viewModelScope.launch {
        moviesRepository.upsert(movies)
    }
}












