package com.example.movietime.ui

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
import com.example.movietime.ui.theme.MovieTimeTheme
import com.example.movietime.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val TAG:String = "MainActivity"
    private val viewModel: MainViewModel by viewModel()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieTimeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting{
                        viewModel.fetchMovies()
                        Log.d(TAG,"${viewModel.getMoviesList().value}")
                    }
                }
            }
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


