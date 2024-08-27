package com.example.movietime.model

data class Movie(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val summary: String,
    val runtime: Int,
    val yearOfRelease: Int,
    val genres: List<Genre>,
    val adult: Boolean?,
    val imageUrl: String = "https://c4.wallpaperflare.com/wallpaper/153/860/948/movie-poster-star-wars-star-wars-the-rise-of-skywalker-2019-year-movies-hd-wallpaper-preview.jpg",
    val releaseDate: String?, // or LocalDate if you prefer working with dates
    val tagline: String?,
    val trailerUrl: String?
)
