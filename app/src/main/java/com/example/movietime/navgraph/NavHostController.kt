package com.example.movietime.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movietime.model.Genre
import com.example.movietime.model.Movie
import com.example.movietime.ui.screens.HomeScreen
import com.example.movietime.ui.screens.MovieDetailsScreen
import com.example.movietime.ui.screens.ProfileScreen
import com.example.movietime.ui.screens.SearchScreen
import java.time.LocalDate

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
                ProfileScreen()
            }

            //route : details
            composable("details"){
                val movie = Movie(
                    id = 1,
                    title = "Star Wars: The Last Jedi",
                    rating = 9.5,
                    voteCount = 123,
                    summary = "Somewhat a story",
                    runtime = 120,
                    yearOfRelease = 2016,
                    genres = listOf(
                        Genre(1, "Family"),
                        Genre(2, "Drama"),
                        Genre(3, "Action"),
                        Genre(4, "Horror")
                    ),
                    adult = false, // Assuming it's not an adult movie
                    imageUrl = "https://example.com/last_jedi.jpg",
                    releaseDate = LocalDate.of(2016, 12, 15),
                    tagline = "The Saga Continues",
                    trailerUrl = "https://example.com/last_jedi-trailer.mp4"
                )
                MovieDetailsScreen(movie){
                    navController.navigateUp()
                }
            }
        }
    )

}