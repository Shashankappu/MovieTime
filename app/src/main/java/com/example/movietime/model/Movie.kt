package com.example.movietime.model

import java.time.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val rating: Double,
    val voteCount: Int,
    val summary: String,
    val runtime: Int,
    val yearOfRelease: Int,
    val genres: List<Genre>,
    val adult: Boolean?,
    val imageUrl: String?,
    val releaseDate: LocalDate?, // or LocalDate if you prefer working with dates
    val tagline: String?,
    val trailerUrl: String?
)
