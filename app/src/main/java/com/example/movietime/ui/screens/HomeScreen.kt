package com.example.movietime.ui.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeRoute(
    mainViewModel: MainViewModel,
    onMovieItemClicked :  (movieData:Movie) -> Unit
){
    HomeScreen(
        mainViewModel = mainViewModel,
        onMovieItemClicked = onMovieItemClicked
    )
}

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    onMovieItemClicked: (movieData : Movie) -> Unit
) {
    val moviesList by mainViewModel.moviesList.collectAsState()
    val topRatedMoviesList by mainViewModel.topRatedMoviesList.collectAsState()

    val appName = buildAnnotatedString {
        withStyle(style = SpanStyle(color = orange)) {
            append(stringResource(R.string.app_name_text_moovy))
        }
        withStyle(style = SpanStyle(color = Color.White)) {
            append(stringResource(R.string.app_name_text_flix))
        }
    }

    LaunchedEffect(Unit) {
        if(moviesList.isEmpty()) mainViewModel.fetchMovies()
        if(topRatedMoviesList.isEmpty()) mainViewModel.fetchTopRatedMovies()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = appName,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
            fontSize = 24.sp
        )

        NowPlayingMovieCard(onMovieItemClicked = onMovieItemClicked)

        Text(
            stringResource(R.string.title_text_top_rated),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
            fontSize = 24.sp
        )

        TopRatedCarousel(
            topRatedMoviesList = topRatedMoviesList,
            onMovieItemClicked = onMovieItemClicked
        )
    }
}

@Composable
fun NowPlayingMovieCard(
    onMovieItemClicked: (movieData:Movie) ->Unit
){
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .size(350.dp, 205.dp)
            .clip(shape = RoundedCornerShape(12))
            .clickableWithoutRipple { onMovieItemClicked(dummyMovies[0]) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://images.unsplash.com/photo-1485846234645-a62644f84728?q=80&w=2059&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.godfather),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        ContinueWatchingButton(
            modifier = Modifier
                .size(230.dp, 70.dp)
                .padding(start = 10.dp, bottom = 10.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFDADADA).copy(alpha = 0.4f),
                            Color(0xFFDADADA).copy(alpha = 0.4f)
                        )
                    ),
                    shape = RoundedCornerShape(30)
                )
                .border(BorderStroke(2.dp, Color.Gray.copy(0.4f)), RoundedCornerShape(30))
                .align(Alignment.BottomStart)
        )
    }
}

@Composable
private fun ContinueWatchingButton(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
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
                    text = stringResource(R.string.title_text_top_rated),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.title_desc_ready_player_one),
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun TopRatedMovieCard(
    movieData : Movie,
    alpha: Float = 1f,
    scale: Float = 1f,
    onMovieItemClicked : (movieData:Movie) -> Unit = {}
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
                onMovieItemClicked(movieData)
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
        MovieTitleCard(
            modifier = Modifier
                .size(230.dp, 70.dp)
                .padding(bottom = 10.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFDADADA).copy(alpha = 0.4f),
                            Color(0xFFDADADA).copy(alpha = 0.4f)
                        )
                    ),
                    shape = RoundedCornerShape(30)
                )
                .border(BorderStroke(2.dp, Color.Gray.copy(0.4f)), RoundedCornerShape(30))
                .align(Alignment.BottomCenter),
            movieTitle = movieData.title
        )

        MovieStarRatingCard(
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
                .border(BorderStroke(2.dp, Color.Gray.copy(0.4f)), RoundedCornerShape(30))
                .align(Alignment.TopEnd),
            movieRating = movieData.voteAverage
        )
    }
}

@Composable
private fun MovieStarRatingCard(
    modifier: Modifier,
    movieRating: Double
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.text_imdb),
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
                text = "$movieRating",
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

@Composable
private fun MovieTitleCard(
    modifier: Modifier,
    movieTitle: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = movieTitle,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopRatedCarousel(
    topRatedMoviesList:List<Movie>,
    onMovieItemClicked: (movieData: Movie) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if(topRatedMoviesList.isNotEmpty()) {
            val pagerState = rememberPagerState(initialPage = 1,pageCount = { topRatedMoviesList.size })
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 50.dp, vertical = 5.dp),
                modifier = Modifier.fillMaxSize(),
            ) { index ->
                val scale = if (pagerState.currentPage == index) 1.05f else 0.85f
                val alpha = if (pagerState.currentPage == index) 1f else 0.85f
                TopRatedMovieCard(
                    movieData = topRatedMoviesList[index],
                    scale = scale,
                    alpha = alpha
                ){
                    onMovieItemClicked(topRatedMoviesList[index])
                }
            }
        }
    }
}