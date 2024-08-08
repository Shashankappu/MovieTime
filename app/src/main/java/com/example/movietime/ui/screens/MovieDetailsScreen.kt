package com.example.movietime.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movietime.R
import com.example.movietime.ui.theme.orange

@Composable
fun MovieDetailsScreen(onBackPressed : ()-> Unit){
    Image(
        painter = painterResource(id = R.drawable.movie_star_wars),
        contentDescription = "image",
        modifier = Modifier
            .fillMaxWidth()
            .height(290.dp),
        contentScale = ContentScale.FillBounds
    )
    Box(
        modifier = Modifier
            .padding(top = 25.dp,start = 20.dp)
            .clip(RoundedCornerShape(50))
            .background(color = Color.Black.copy(0.5f))
            .clickable { onBackPressed() }
    ){
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "back icon",
            modifier = Modifier.size(42.dp),
            tint = Color.White
        )
    }
    Box(
        modifier = Modifier
            .padding(top = 110.dp,start = 165.dp)
            .size(64.dp)
            .clip(RoundedCornerShape(50))
            .background(color = Color.White.copy(0.3f)),
        contentAlignment = Alignment.Center
    ){
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "play icon",
            modifier = Modifier.size(42.dp),
            tint = orange
        )
    }
}