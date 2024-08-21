package com.example.movietime.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movietime.R
import com.example.movietime.extension.clickableWithoutRipple
import com.example.movietime.model.Movie
import com.example.movietime.ui.theme.orange
import com.example.movietime.utils.dummyMovies
import com.example.movietime.viewmodels.MainViewModel
import org.koin.androidx.compose.koinViewModel

private  val TAG = "HomeScreen"
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(onClick: () -> Unit) {
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
        NowPlayingMovieCard(onClick)
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
fun NowPlayingMovieCard(onClick:()->Unit){
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .size(350.dp, 205.dp)
            .clip(shape = RoundedCornerShape(12))
            .clickableWithoutRipple { onClick() }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://images.unsplash.com/photo-1485846234645-a62644f84728?q=80&w=2059&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.godfather),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .size(230.dp, 70.dp)
                .padding(start = 10.dp, bottom = 10.dp)
                .background(Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFDADADA).copy(alpha=0.4f),
                        Color(0xFFDADADA).copy(alpha=0.4f)
                    )
                ),shape = RoundedCornerShape(30))
                .border(BorderStroke(2.dp,Color.Gray.copy(0.4f)),RoundedCornerShape(30))
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
    movieData : Movie,
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
            .clickableWithoutRipple {
                onClick()
            }
            .clip(shape = RoundedCornerShape(12)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieData.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.godfather),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .size(230.dp, 70.dp)
                .padding(bottom = 10.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFDADADA).copy(alpha = 0.4f),
                            Color(0xFFDADADA).copy(alpha = 0.4f)
                        )
                    ),
                    shape = RoundedCornerShape(30)
                )
                .border(BorderStroke(2.dp,Color.Gray.copy(0.4f)),RoundedCornerShape(30))
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
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFDADADA).copy(alpha = 0.5f),
                            Color(0xFFDADADA).copy(alpha = 0.5f)
                        )
                    ),
                    shape = RoundedCornerShape(30)
                )
                .border(BorderStroke(2.dp,Color.Gray.copy(0.4f)),RoundedCornerShape(30))
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
                            .weight(0.5f)
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

//val moviesData = listOf(
//    MoviesData("The God father",R.drawable.godfather,8.5f,1983),
//    MoviesData("Star Wars",R.drawable.movie_star_wars,7.0f,2010),
//    MoviesData("The Mongol",R.drawable.mongol2,6.0f,2001),
//    MoviesData("Thor",R.drawable.thor,8.0f,2019),
//    MoviesData("Avatar",R.drawable.avatar,6.0f,2016),
//    MoviesData("Fire",R.drawable.fire,9.0f,2000)
//)

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingCarousel() {
    Box(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 1, pageCount = { dummyMovies.size })
        HorizontalPager(
            state = pagerState,
            contentPadding= PaddingValues(horizontal = 50.dp, vertical = 5.dp),
            modifier = Modifier.fillMaxSize(),
        ) { index ->
            val scale = if(pagerState.currentPage == index) 1.05f else 0.85f
            val alpha = if(pagerState.currentPage == index) 1f else 0.85f
            TrendingMovieCard(dummyMovies[index],scale = scale, alpha = alpha)
        }
    }
}