package com.example.movietime.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movietime.ui.screens.EditProfileScreen
import com.example.movietime.ui.screens.HomeScreen
import com.example.movietime.ui.screens.MovieDetailsScreen
import com.example.movietime.ui.screens.ProfileScreen
import com.example.movietime.ui.screens.SearchScreen
import com.example.movietime.utils.dummyMovies

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {

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
                ProfileScreen{
                    navController.navigate("edit_profile")
                }
            }

            //route : details
            composable("details"){
                val movie = dummyMovies[0]
                MovieDetailsScreen(movie){
                    navController.navigateUp()
                }
            }

            composable("edit_profile"){
                EditProfileScreen {
                    navController.navigateUp()
                }
            }
        }
    )

}