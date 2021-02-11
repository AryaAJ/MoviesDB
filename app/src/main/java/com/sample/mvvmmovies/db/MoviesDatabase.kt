package com.sample.mvvmmovies.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.mvvmmovies.models.moviedb.Result

@Database(
    entities = [Result::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}