package com.example.movietime.ui.screens

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movietime.navgraph.Screen
import com.example.movietime.ui.theme.orange
import com.example.movietime.utils.Constants

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route
    if(currentRoute!=Screen.USER_REGISTRATION) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.background
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
                            tint =
                                if (isSelected) orange
                                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
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