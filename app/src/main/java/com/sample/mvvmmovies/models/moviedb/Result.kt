package com.sample.mvvmmovies.models.moviedb

import androidx.room.Entity
import java.io.Serializable

@Entity(
    tableName = "movies",
    primaryKeys = [("id")]
)
data class Result(
    val id: Int,
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Float?,
    val vote_count: String?
) : Serializable