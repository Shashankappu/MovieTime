package com.example.movietime.ui.screens

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movietime.ui.theme.bgPurple
import com.example.movietime.ui.theme.orange
import com.example.movietime.utils.Constants

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route
    if(currentRoute!="user_registration") {
        BottomNavigation(
            backgroundColor = bgPurple
        ) {
            Constants.BottomNavItems.forEach { navItem ->
                val isSelected = currentRoute == navItem.route
                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(navItem.route) {
                            // Pop up to the start destination to avoid building up a large backstack
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = navItem.icon),
                            contentDescription = navItem.label,
                            tint = if (isSelected) orange else Color.Gray.copy(alpha = 0.3f)
                        )
                    },
                    label = {
                        Text(text = navItem.label)
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}