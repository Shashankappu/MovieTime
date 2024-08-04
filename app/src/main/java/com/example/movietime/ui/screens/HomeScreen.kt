package com.example.movietime.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.R
import com.example.movietime.ui.theme.orange
import com.example.movietime.viewmodels.MainViewModel
import org.koin.androidx.compose.koinViewModel

private  val TAG = "HomeScreen"
@Composable
fun HomeScreen() {
    val mainViewModel: MainViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        mainViewModel.fetchMovies()
    }
    Column{
        Text(
            "MoovY Flix",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
            fontSize = 26.sp
        )
        NowPlayingMovieCard()
        Text(
            "Trending",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
            fontSize = 26.sp
        )
        TrendingMovieCard()
    }
    //ListView(mainViewModel)
}

//@Composable
//fun ListView(mainViewModel: MainViewModel){
//    val moviesList = mainViewModel.moviesList.observeAsState(emptyList()).value
//    LazyColumn(
//        modifier = Modifier
//            .padding(top = 150.dp,start = 10.dp, end = 10.dp, bottom = 10.dp)
//    ) {
//        itemsIndexed(moviesList){ _,item ->
//            Text(item.title,
//                modifier = Modifier
//                    .fillMaxSize(),
//                fontSize = 20.sp,
//                color = Color.Red
//            )
//        }
//    }
//}

@Composable
fun NowPlayingMovieCard(){
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .size(350.dp, 205.dp)
            .clip(shape = RoundedCornerShape(12))
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id= R.drawable.movie_sample_image),
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .size(230.dp, 70.dp)
                .padding(start = 10.dp, bottom = 10.dp)
                .align(Alignment.BottomStart)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(25))
                    .background(color = Color.Gray.copy(alpha = 0.6f))
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                // Play button
                Icon(
                    painter = painterResource(id = R.drawable.play),
                    tint = orange,
                    contentDescription = "play"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "Continue Watching",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Ready Player One",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TrendingMovieCard(){
    Box(
        modifier = Modifier
            .padding(horizontal = 50.dp, vertical = 10.dp)
            .size(280.dp, 364.dp)
            .clip(shape = RoundedCornerShape(12)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id= R.drawable.movie_star_wars),
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .size(230.dp, 70.dp)
                .padding(bottom = 10.dp)
                .clip(shape = RoundedCornerShape(22))
                .background(color = Color.Gray.copy(alpha = 0.6f))
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Star Wars: The Last Jedi",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(75.dp, 50.dp)
                .clip(shape = RoundedCornerShape(22))
                .background(color = Color.Gray.copy(alpha = 0.6f))
                .align(Alignment.TopEnd),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(5.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ){
                Column(
                    modifier = Modifier.wrapContentSize(Alignment.TopStart),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "IMDb",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.star_rating_icon),
                        contentDescription = "Star",
                        tint = Color(0xFFF3BE00)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "7.0",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxSize().padding(top=20.dp)
                )
            }
        }
    }
}