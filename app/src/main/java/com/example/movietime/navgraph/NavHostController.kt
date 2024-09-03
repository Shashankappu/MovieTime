package com.example.movietime.navgraph

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movietime.model.Movie
import com.example.movietime.ui.screens.EditProfileScreen
import com.example.movietime.ui.screens.HomeScreen
import com.example.movietime.ui.screens.MovieDetailsScreen
import com.example.movietime.ui.screens.ProfileScreen
import com.example.movietime.ui.screens.SearchScreen
import com.example.movietime.ui.screens.UserRegistrationScreen
import com.google.gson.Gson

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screen.USER_REGISTRATION,
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable(route=Screen.USER_REGISTRATION){
                UserRegistrationScreen {
                    navController.navigate("home")
                }
            }
            composable(Screen.HOME) {
                HomeScreen { movie ->
                    val movieJson = Uri.encode(Gson().toJson(movie))
                    navController.navigate("details/$movieJson"){
                        launchSingleTop = true
                        popUpTo("details") {
                            inclusive = true
                        }
                    }
                }
            }

            // route : search
            composable(Screen.SEARCH) {
                SearchScreen{ movie ->
                    val movieJson = Uri.encode(Gson().toJson(movie))
                    navController.navigate("details/$movieJson"){
                        launchSingleTop = true
                        popUpTo("details") {
                            inclusive = true
                        }
                    }
                }
            }

            // route : profile
            composable(Screen.PROFILE) {
                ProfileScreen{
                    navController.navigate("edit_profile")
                }
            }

            //route : details
            composable(
                route = "details/{movieData}",
                arguments = listOf(navArgument("movieData") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val movieJson = backStackEntry.arguments?.getString("movieData")
                val movie = Gson().fromJson(movieJson, Movie::class.java)
                MovieDetailsScreen(movie) {
                    navController.navigateUp()
                }
            }

            composable(Screen.EDIT_PROFILE){
                EditProfileScreen {
                    navController.navigateUp()
                }
            }
        }
    )

}