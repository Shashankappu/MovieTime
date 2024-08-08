package com.example.movietime.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movietime.ui.screens.HomeScreen
import com.example.movietime.ui.screens.MovieDetailsScreen
import com.example.movietime.ui.screens.ProfileScreen
import com.example.movietime.ui.screens.SearchScreen

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "home",

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {

            // route : Home
            composable("home") {
                HomeScreen{
                    navController.navigate("details")
                }
            }

            // route : search
            composable("search") {
                SearchScreen{
                    navController.navigate("details")
                }
            }

            // route : profile
            composable("profile") {
                ProfileScreen()
            }

            //route : details
            composable("details"){
                MovieDetailsScreen{
                    navController.navigateUp()
                }
            }
        }
    )

}