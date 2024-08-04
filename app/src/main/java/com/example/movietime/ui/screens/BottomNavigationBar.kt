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

    BottomNavigation(
        backgroundColor = bgPurple
    ) {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        Constants.BottomNavItems.forEach { navItem ->
            val isSelected = currentRoute == navItem.route
            // Place the bottom nav items
            BottomNavigationItem(

                // it currentRoute is equal then its selected route
                selected = isSelected,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route) {
                        // Pop up to the start destination to avoid building up a large backstack
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },

                // Icon of navItem
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.icon),
                        contentDescription = navItem.label,
                        tint =  if (isSelected) orange else Color.Gray.copy(alpha = 0.3f)
                    )
                },

                // label
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}