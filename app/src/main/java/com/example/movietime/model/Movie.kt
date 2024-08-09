package com.example.movietime.model

data class Movie(
    val id: Int,
    val title: String,
    val rating: Double,
    val voteCount: Int,
    val summary: String?,
    val runtime: Int,
    val yearOfRelease : Int,
    val genres: List<Genre>
)
