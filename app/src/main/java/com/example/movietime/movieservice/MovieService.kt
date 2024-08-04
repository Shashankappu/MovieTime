package com.example.movietime.movieservice


import com.example.movietime.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movies")
    suspend fun getMovies() : List<Movie>

    @GET("movies/{movieId}")
    suspend fun getMovieById(@Path("movieId") movieId: Long) : Movie

}