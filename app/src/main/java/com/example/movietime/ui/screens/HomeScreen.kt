package com.example.movietime.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.FloatState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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
        TrendingCarousel()
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
fun TrendingMovieCard(
    resId :Int = R.drawable.movie_star_wars,
    rating:Float = 7.0f,
    title:String = "Star Wars:The Last Jedi",
    alpha: Float=1f,
    scale:Float = 1f
){
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .size(250.dp, 350.dp)
            .alpha(alpha)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(shape = RoundedCornerShape(12)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id= resId),
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
                text = title,
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
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
                    text = rating.toString(),
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                )
            }
        }
    }
}

@Composable
fun TrendingCarousel() {
    val movieList = listOf(
        R.drawable.movie_star_wars,
        R.drawable.movie_star_wars,
        R.drawable.movie_star_wars
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 50.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(movieList) { index, movie ->
            val alpha = if (index == 1) 1f else 0.5f // Dim the side items
            val scale = if (index == 1) 1f else 0.85f // Scale the middle item larger

            TrendingMovieCard(movie,5.5f,"Jingle Jingle",alpha,scale)
        }
    }
}