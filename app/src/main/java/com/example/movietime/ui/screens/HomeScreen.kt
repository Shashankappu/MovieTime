package com.example.movietime.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.R
import com.example.movietime.ui.theme.orange
import com.example.movietime.utils.MoviesData
import com.example.movietime.viewmodels.MainViewModel
import org.koin.androidx.compose.koinViewModel

private  val TAG = "HomeScreen"
@Composable
fun HomeScreen() {
    val mainViewModel: MainViewModel = koinViewModel()
    val appNametext = buildAnnotatedString {
        withStyle(style = SpanStyle(color = orange)) {
            append("MoovY")
        }
        withStyle(style = SpanStyle(color = Color.White)) {
            append(" Flix")
        }
    }
    LaunchedEffect(Unit) {
        mainViewModel.fetchMovies()
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = appNametext,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
            fontSize = 24.sp
        )
        NowPlayingMovieCard()
        Text(
            "Trending",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
            fontSize = 24.sp
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
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .size(230.dp, 70.dp)
                .padding(start = 10.dp, bottom = 10.dp)
                .clip(shape = RoundedCornerShape(30))
                .background(Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFDADADA).copy(alpha=0.4f),
                        Color(0xFFDADADA).copy(alpha=0.4f)
                    )
                ))
                .align(Alignment.BottomStart)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
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
    movieData : MoviesData,
    alpha: Float = 1f,
    scale: Float = 1f,
    onClick : () -> Unit = {}
){
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .size(260.dp, 350.dp)
            .alpha(alpha)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable {
                onClick()
            }
            .clip(shape = RoundedCornerShape(12)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = movieData.image),
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .size(230.dp, 70.dp)
                .padding(bottom = 10.dp)
                .clip(shape = RoundedCornerShape(30))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFDADADA).copy(alpha = 0.4f),
                            Color(0xFFDADADA).copy(alpha = 0.4f)
                        )
                    )
                )
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = movieData.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(78.dp, 46.dp)
                .clip(shape = RoundedCornerShape(30))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFDADADA).copy(alpha = 0.5f),
                            Color(0xFFDADADA).copy(alpha = 0.5f)
                        )
                    )
                )
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
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "IMDb",
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.5f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.star_rating_icon),
                        contentDescription = "Star",
                        tint = Color(0xFFF3BE00),
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.85f)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = movieData.rating.toString(),
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(top = 15.dp)
                )
            }
        }
    }
}

val moviesData = listOf(
    MoviesData("The God father",R.drawable.godfather,8.5f),
    MoviesData("Star Wars",R.drawable.movie_star_wars,7.0f),
    MoviesData("The Mongol",R.drawable.mongol2,6.0f),
    MoviesData("Thor",R.drawable.thor,8.0f),
    MoviesData("Avatar",R.drawable.avatar,6.0f),
    MoviesData("Fire",R.drawable.fire,9.0f)
)

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingCarousel() {
    Box(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 1, pageCount = { moviesData.size })
        HorizontalPager(
            state = pagerState,
            contentPadding= PaddingValues(horizontal = 50.dp, vertical = 5.dp),
            modifier = Modifier.fillMaxSize(),
        ) { index ->
            val scale = if(pagerState.currentPage == index) 1.05f else 0.85f
            val alpha = if(pagerState.currentPage == index) 1f else 0.85f
            TrendingMovieCard(moviesData[index],scale = scale, alpha = alpha)
        }
    }
}