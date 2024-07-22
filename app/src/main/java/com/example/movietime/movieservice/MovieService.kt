package com.example.movietime.movieservice


import com.example.movietime.model.Movie
import retrofit2.http.GET

interface MovieService {

    @GET("movies")
    suspend fun getMovies() : List<Movie>

}