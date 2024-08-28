package com.example.movietime.movieservice


import com.example.movietime.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movies")
    suspend fun getMovies() : List<Movie>

    @GET("movies/top-rated")
    suspend fun getTopRatedMovies() : List<Movie>

    @GET("movies/by-genre")
    suspend fun getMovieByGenre(@Query("genre") genre: String) : List<Movie>
}