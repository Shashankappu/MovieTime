package com.example.movietime.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movietime.R
import com.example.movietime.extension.clickableWithoutRipple
import com.example.movietime.model.Genre
import com.example.movietime.model.Movie
import com.example.movietime.ui.theme.orange
import com.example.movietime.utils.dummyMovies
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen() {
    val movie = Movie(
        id = 1,
        title = "Star Wars: The Last Jedi",
        rating = 9.5,
        voteCount = 123,
        summary = "Somewhat a story",
        runtime = 120,
        yearOfRelease = 2016,
        genres = listOf(
            Genre(1, "Family"),
            Genre(2, "Drama"),
            Genre(3, "Action"),
            Genre(4, "Horror")
        ),
        adult = false, // Assuming it's not an adult movie
        imageUrl = "https://example.com/last_jedi.jpg",
        releaseDate = LocalDate.of(2016, 12, 15),
        tagline = "The Saga Continues",
        trailerUrl = "https://example.com/last_jedi-trailer.mp4"

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy((-35).dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "profile image",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(50))
                        .border(
                            border = BorderStroke(2.dp, Color.Gray.copy(0.4f)),
                            shape = RoundedCornerShape(50)
                        ),
                    contentScale = ContentScale.FillBounds
                )
                Image(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "profile image",
                    modifier = Modifier
                        .size(35.dp)
                        .clip(RoundedCornerShape(50))
                        .border(
                            border = BorderStroke(2.dp, Color.Gray.copy(0.4f)),
                            shape = RoundedCornerShape(50)
                        )
                        .background(orange),
                    contentScale = ContentScale.Inside
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Shashank",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    fontSize = 18.sp
                )
                Text(
                    text = "shashanksp1512@gmail.com",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
        Text(
            text = "Favourite Genres:",
            color = Color.White,
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp)
                .fillMaxWidth(),
            fontSize = 18.sp
        )
        Row(
            modifier = Modifier.padding(start = 10.dp, end=20.dp,top = 10.dp)
        ) {
            var times = movie.genres.size
            repeat(times) {
                times--
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(60.dp, 25.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0x1FBCBCBC),
                                    Color(0x0DFAF0CA)
                                )
                            ),
                            shape = RoundedCornerShape(50)
                        )
                        .border(
                            BorderStroke(1.dp, Color.Gray.copy(0.4f)),
                            RoundedCornerShape(50)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = movie.genres[times].genreName,
                        modifier = Modifier,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
        Text(
            text = "Watched Movies :",
            color = Color.White,
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp)
                .fillMaxWidth(),
            fontSize = 24.sp
        )
        Column(
            modifier = Modifier.padding(top = 10.dp)
        ){
            WatchedMoviesCarousel()
            WatchedMoviesCarousel()
            WatchedMoviesCarousel()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WatchedMoviesCarousel(onClick:()->Unit = {}){
    LazyRow(
        modifier = Modifier
            .padding(top = 10.dp, start = 5.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        items(dummyMovies.size) { index->
            val movie = dummyMovies[index]
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 10.dp)
                    .clickableWithoutRipple {
                        onClick()
                    },
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
//                Image(
//                    painter = painterResource(id = movie.image),
//                    contentDescription = "movie image",
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(16))
//                        .height(110.dp)
//                        .width(150.dp),
//                    contentScale = ContentScale.FillBounds
//                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.godfather),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16))
                        .height(110.dp)
                        .width(150.dp),
                )
                Text(
                    text = "${movie.title}\n(${movie.yearOfRelease})",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    maxLines = 2,
                    lineHeight = 14.sp
                )
            }
        }
    }
}
