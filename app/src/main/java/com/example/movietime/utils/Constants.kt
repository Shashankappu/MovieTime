package com.example.movietime.utils

import com.example.movietime.R
import com.example.movietime.model.Genre
import com.example.movietime.model.Movie

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = R.drawable.home_icon,
            route = "home"
        ),
        BottomNavItem(
            label = "Search",
            icon = R.drawable.play,
            route = "search"
        ),
        BottomNavItem(
            label = "Profile",
            icon = R.drawable.user,
            route = "profile"
        )
    )
}

val dummyMovies = listOf(
    Movie(
        id = 1,
        title = "The Godfather",
        voteAverage = 8.5,
        voteCount = 1200000,
        summary = "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
        runtime = 175,
        yearOfRelease = 1983,
        genres = listOf(Genre(1, "Crime"), Genre(2, "Drama"),Genre(4, "Adventure"),Genre(5, "History")),
        adult = true,
        imageUrl = "https://imgs.search.brave.com/pbA7p0uoB5HP1uEe2EOwTAOsNE-Coe6-8xqcbhOWBA8/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tLm1l/ZGlhLWFtYXpvbi5j/b20vaW1hZ2VzL00v/TVY1Qk0yTXlOall4/Tm1VdFlUQXdOaTAw/TVRZeExXSm1OV1l0/WXpabE9EWTNaVGsz/T1RGbFhrRXlYa0Zx/Y0dkZVFYVnlOemt3/TWpRNU56TUAuanBn",
        releaseDate ="1983",
        tagline = "An offer you can't refuse.",
        trailerUrl = "https://example.com/godfather-trailer.mp4"
    ),
    Movie(
        id = 2,
        title = "Star Wars",
        voteAverage = 7.0,
        voteCount = 2200000,
        summary = "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee, and two droids to save the galaxy from the Empire.",
        runtime = 121,
        yearOfRelease = 2010,
        genres = listOf(Genre(3, "Action"), Genre(4, "Adventure")),
        adult = false,
        imageUrl = "https://c4.wallpaperflare.com/wallpaper/153/860/948/movie-poster-star-wars-star-wars-the-rise-of-skywalker-2019-year-movies-hd-wallpaper-preview.jpg",
        releaseDate = "2010, 5, 25",
        tagline = "A long time ago in a galaxy far, far away...",
        trailerUrl = "https://example.com/star_wars-trailer.mp4"
    ),
    Movie(
        id = 3,
        title = "The Mongol",
        voteAverage = 6.0,
        voteCount = 850000,
        summary = "The story of Genghis Khan's rise to power.",
        runtime = 120,
        yearOfRelease = 2001,
        genres = listOf(Genre(5, "History"), Genre(2, "Drama")),
        adult = true,
        imageUrl = "https://m.media-amazon.com/images/M/MV5BODlhNTk4ODYtOThlMy00N2E1LThkZDktZjZlMjE1YjM3YTgwL2ltYWdlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg",
        releaseDate ="2001, 9, 15",
        tagline = "The Untold Story of the Great Conqueror.",
        trailerUrl = "https://example.com/mongol-trailer.mp4"
    ),
    Movie(
        id = 4,
        title = "Thor",
        voteAverage = 8.0,
        voteCount = 1700000,
        summary = "The powerful but arrogant god Thor is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.",
        runtime = 115,
        yearOfRelease = 2019,
        genres = listOf(Genre(3, "Action"), Genre(6, "Fantasy")),
        adult = false,
        imageUrl = "https://m.media-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_.jpg",
        releaseDate ="2019, 5 2",
        tagline = "The God of Thunder.",
        trailerUrl = "https://example.com/thor-trailer.mp4"
    ),
    Movie(
        id = 5,
        title = "Avatar",
        voteAverage = 6.0,
        voteCount = 1800000,
        summary = "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
        runtime = 162,
        yearOfRelease = 2016,
        genres = listOf(Genre(7, "Sci-Fi"), Genre(4, "Adventure")),
        adult = false,
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyEW3ADcZeKxOOjEXPy6rDYmq4kulo-R6tkg&s",
        releaseDate = "2016, 12, 18",
        tagline = "Enter the World of Pandora.",
        trailerUrl = "https://example.com/avatar-trailer.mp4"
    ),
    Movie(
        id = 6,
        title = "Fire",
        voteAverage = 9.0,
        voteCount = 950000,
        summary = "A gripping story of survival and hope against all odds.",
        runtime = 130,
        yearOfRelease = 2000,
        genres = listOf(Genre(8, "Drama"), Genre(9, "Thriller")),
        adult = true,
        imageUrl = "https://m.media-amazon.com/images/M/MV5BMjIxNzY5MjIzNV5BMl5BanBnXkFtZTgwNTIwMzgwMzE@._V1_.jpg",
        releaseDate = "2000, 8, 20",
        tagline = "The Flame of Courage.",
        trailerUrl = "https://example.com/fire-trailer.mp4"
    )
)