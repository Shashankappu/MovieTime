package com.example.movietime.model

data class Movie(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val summary: String?,
    val genres: List<Genre>
)
