package com.example.movietime.utils

import androidx.compose.material3.Icon
import com.example.movietime.R

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = R.drawable.home_icon,
            route = "home"
        ),
        BottomNavItem(
            label = "Search",
            icon = R.drawable.play,
            route = "search"
        ),
        BottomNavItem(
            label = "Profile",
            icon = R.drawable.user,
            route = "profile"
        )
    )
}