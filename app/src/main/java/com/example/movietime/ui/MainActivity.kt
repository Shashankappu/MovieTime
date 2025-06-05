package com.example.movietime.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.movietime.navgraph.AppNavHost
import com.example.movietime.ui.screens.BottomNavigationBar
import com.example.movietime.ui.theme.MovieTimeTheme
import com.example.movietime.ui.theme.bgPurple

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
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }
                    )
                    { padding ->
                        AppNavHost(navController = navController, padding = padding)
                    }
                }

            }
        }
    }
}




