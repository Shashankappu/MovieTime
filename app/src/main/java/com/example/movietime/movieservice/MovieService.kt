package com.example.movietime.movieservice


import com.example.movietime.model.Movie
import com.example.movietime.utils.Constants.BASE_URL
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(BASE_URL)
    suspend fun getMovies() : List<Movie>

    @GET("$BASE_URL/top-rated")
    suspend fun getTopRatedMovies() : List<Movie>

    @GET("$BASE_URL/by-genre")
    suspend fun getMovieByGenre(@Query("genre") genre: String) : List<Movie>

    @GET("$BASE_URL/by-genres")
    suspend fun getMovieByMultipleGenres(@Query("genres") genres: String) : List<Movie>

    @GET("$BASE_URL/search")
    suspend fun getMovieBySearchQuery(@Query("query") query: String,@Query("genreName") genreName:String?) : List<Movie>
}