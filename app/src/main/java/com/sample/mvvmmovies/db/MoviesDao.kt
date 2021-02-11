package com.sample.mvvmmovies.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sample.mvvmmovies.models.moviedb.Result

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Result): Long

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Result>>

    @Delete
    suspend fun deleteArticle(movie: Result)
}