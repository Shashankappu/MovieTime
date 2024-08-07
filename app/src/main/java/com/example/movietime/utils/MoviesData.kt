package com.example.movietime.utils

import com.example.movietime.R

data class MoviesData(
    val title : String = "",
    val image : Int = R.drawable.movie_star_wars,
    val rating : Float = 7.0f,
    val yearOfRelease : Int = 2000
)