package com.example.movietime.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.R
import com.example.movietime.model.Movie
import com.example.movietime.ui.theme.orange

@Composable
fun MovieDetailsScreen(movie:Movie,onBackPressed : ()-> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        MovieImageAndPlayComposable {
            onBackPressed()
        }
        MovieDetails(movie)
    }
}

@Composable
fun MovieDetails(movie: Movie){
    Column {
        Text(
            text = movie.title,
            modifier = Modifier.padding(top = 12.dp, start = 20.dp),
            fontSize = 24.sp,
            color = Color.White
        )
        Row(
            modifier = Modifier
                .size(220.dp, 24.dp)
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.clock),
                modifier = Modifier
                    .size(18.dp)
                    .padding(2.dp),
                contentDescription = "runtime"
            )
            Text(
                text = "${movie.runtime} minutes",
                modifier = Modifier,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                modifier = Modifier
                    .size(18.dp)
                    .padding(2.dp)
            )
            Text(
                text = "${movie.rating} minutes",
                modifier = Modifier,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Divider(modifier = Modifier
            .width(320.dp)
            .padding(top = 10.dp, start = 20.dp),
            color = Color(0xFF515151)
        )
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                Text(
                    text = "Release Date",
                    modifier = Modifier,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = "Genre",
                    modifier = Modifier,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(120.dp)
            ) {
                Text(
                    text = "${movie.yearOfRelease}",
                    modifier = Modifier,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Row {
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .wrapContentSize()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0x1FBCBCBC),
                                        Color(0x0DFAF0CA)
                                    )
                                ),
                                shape = RoundedCornerShape(40)
                            )
                    ) {
                        Text(
                            text = "Family",
                            modifier = Modifier,
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .wrapContentSize()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0x1FBCBCBC),
                                        Color(0x0DFAF0CA)
                                    )
                                ),
                                shape = RoundedCornerShape(40)
                            )
                    ) {
                        Text(
                            text = "Drama",
                            modifier = Modifier,
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                }

            }
        }
        Divider(
            modifier = Modifier
                .width(320.dp)
                .padding(top = 10.dp, start = 20.dp),
            color = Color(0xFF515151)
        )
    }
}

@Composable
fun MovieImageAndPlayComposable(onBackPressed : ()-> Unit){
    Box{
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
                .padding(top = 25.dp, start = 20.dp)
                .clip(RoundedCornerShape(50))
                .background(color = Color.Black.copy(0.5f))
                .clickable { onBackPressed() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "back icon",
                modifier = Modifier.size(42.dp),
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 110.dp, start = 145.dp)
                .size(64.dp)
                .clip(RoundedCornerShape(50))
                .background(color = Color.White.copy(0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "play icon",
                modifier = Modifier.size(42.dp),
                tint = orange
            )
        }
    }
}