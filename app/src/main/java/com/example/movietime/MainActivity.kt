package com.example.movietime

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.model.Movie
import com.example.movietime.movieservice.MovieService
import com.example.movietime.ui.theme.MovieTimeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private val BASE_URL = "http://local_ip_address:port_number/"
    private val TAG:String = "Shashank"
    private var moviesList:List<Movie> = listOf()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieTimeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting{
                        CoroutineScope(Dispatchers.IO).launch {
                            getAllMovies()
                            for(movies in moviesList){
                                Log.d(TAG,movies.toString())
                            }
                        }
                    }
                }
            }
        }
    }
    private suspend fun getAllMovies(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)

        try {
            val movies = api.getMovies()
            moviesList = movies
        } catch (e: Exception) {
            Log.d(TAG, "Error fetching movies: ${e.message}")
        }
    }
}

@Composable
fun Greeting(onClick:()-> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 100.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            .clickable {
                onClick()
            }
            .background(Color.Red)
    ){
        Text(
            text = "Get Movies",
            fontSize = 40.sp,
        )
    }
}


