package com.example.movietime.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietime.model.Movie
import com.example.movietime.movieservice.MovieService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val movieService: MovieService) : ViewModel(){
    private val TAG:String = "MainViewModel"
    private val _query = MutableStateFlow("")
    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    private val _searchedMoviesList = MutableStateFlow<List<Movie>>(emptyList())
    private val _topRatedMoviesList = MutableStateFlow<List<Movie>>(emptyList())
    private val _moviesByGenreList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList: MutableStateFlow<List<Movie>> get() = _moviesList

    fun getQuery(): StateFlow<String> = _query
    val searchedMoviesList: MutableStateFlow<List<Movie>> get() = _searchedMoviesList
    val topRatedMoviesList: MutableStateFlow<List<Movie>> get() = _topRatedMoviesList
    val moviesByGenreList : MutableStateFlow<List<Movie>> get() = _moviesByGenreList

    fun setQuery(query: String){
        _query.value = query
    }
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
                _moviesByGenreList.value = movies
            } catch (e: Exception) {
                Log.d(TAG,"$e")
            }
        }
    }

    fun fetchMoviesBySearchQuery(query : String,genreName:String?) {
        viewModelScope.launch {
            try {
                val movies = movieService.getMovieBySearchQuery(query,genreName)
                _searchedMoviesList.value = movies
            } catch (e: Exception) {
                Log.d(TAG,"$e")
            }
        }
    }
}