package com.example.movietime.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietime.model.Movie
import com.example.movietime.movieservice.MovieService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val movieService: MovieService) : ViewModel(){
    private val TAG:String = "MainViewModel"
    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    private val _topRatedMoviesList = MutableStateFlow<List<Movie>>(emptyList())
    private val _moviesByGenreList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList: MutableStateFlow<List<Movie>> get() = _moviesList
    val topRatedMoviesList: MutableStateFlow<List<Movie>> get() = _topRatedMoviesList
    val moviesByGenreList : MutableStateFlow<List<Movie>> get() = _moviesByGenreList

    fun fetchTopRatedMovies() {
        viewModelScope.launch {
            try {
                val topRatedMovies = movieService.getTopRatedMovies()
                _topRatedMoviesList.value = topRatedMovies
            } catch (e: Exception) {
                // Handle the error
                Log.d(TAG,"$e")
            }
        }
    }

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val movies = movieService.getMovies()
                _moviesList.value = movies
            } catch (e: Exception) {
                // Handle the error
                Log.d(TAG,"$e")
            }
        }
    }

    fun fetchMoviesByGenre(genreName : String) {
        viewModelScope.launch {
            try {
                val movies = movieService.getMovieByGenre(genreName)
                moviesByGenreList.value = movies
            } catch (e: Exception) {
                Log.d(TAG,"$e")
            }
        }
    }
}