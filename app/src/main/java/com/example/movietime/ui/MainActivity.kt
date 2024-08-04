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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.movietime.navgraph.NavHostContainer
import com.example.movietime.ui.screens.BottomNavigationBar
import com.example.movietime.ui.theme.MovieTimeTheme
import com.example.movietime.ui.theme.bgPurple
import com.example.movietime.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val TAG:String = "MainActivity"
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieTimeTheme {
                val navController = rememberNavController()
                Surface(color = bgPurple) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = { BottomNavigationBar(navController = navController)})
                    { padding ->
                        NavHostContainer(navController = navController, padding = padding)
                    }
                }

            }
        }
    }
}




