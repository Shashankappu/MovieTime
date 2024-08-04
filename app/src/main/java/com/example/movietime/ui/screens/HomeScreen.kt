package com.example.movietime.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.viewmodels.MainViewModel
import org.koin.androidx.compose.koinViewModel

private  val TAG = "HomeScreen"
@Composable
fun HomeScreen() {
    val mainViewModel: MainViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        mainViewModel.fetchMovies()
    }
    Greeting{
        mainViewModel.fetchMovies()
        Log.d(TAG,"${mainViewModel.fetchMovies()}")
    }
    ListView(mainViewModel)
}

@Composable
fun ListView(mainViewModel: MainViewModel){
    val moviesList = mainViewModel.moviesList.observeAsState(emptyList()).value
    LazyColumn(
        modifier = Modifier
            .padding(top = 150.dp,start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        itemsIndexed(moviesList){ _,item ->
            Text(item.title,
                modifier = Modifier
                    .fillMaxSize(),
                fontSize = 20.sp,
                color = Color.Red
            )
        }
    }
}

@Composable
fun Greeting(onClick:()-> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 100.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            .background(Color.Red)
            .clickable {
                onClick()
            }
    ){
        Text(
            text = "Get Movies",
            fontSize = 40.sp,
        )
    }
}