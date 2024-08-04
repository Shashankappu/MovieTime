package com.example.movietime.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movietime.model.Movie
import com.example.movietime.movieservice.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val movieService: MovieService) : ViewModel(){
    private val TAG:String = "MainViewModel"
    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> get() = _moviesList
    fun getMovieById(movieId:Long) = liveData(Dispatchers.IO) {
        try {
            val movieById :Movie  = movieService.getMovieById(movieId)
            Log.d(TAG, "movie by Id is : $movieById")
            emit(movieById)
        } catch (e: Exception) {
            Log.d(TAG, "Error fetching movies: $e")
            emit(null)
        }
    }

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val movies = movieService.getMovies()
                _moviesList.postValue(movies)
            } catch (e: Exception) {
                // Handle the error
                Log.d(TAG,"$e")
            }
        }
    }
}