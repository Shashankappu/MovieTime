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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movietime.R
import com.example.movietime.extension.clickableWithoutRipple
import com.example.movietime.model.Movie
import com.example.movietime.ui.theme.orange
import com.example.movietime.utils.dummyMovies

@Composable
fun MovieDetailsScreen(movie:Movie,onBackPressed : ()-> Unit){
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        CurrentMovieImage()
        MovieDetails(movie)
    }
    Box(
        modifier = Modifier
            .padding(top = 25.dp, start = 20.dp)
            .clip(RoundedCornerShape(50))
            .clickableWithoutRipple { onBackPressed() }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "back icon",
            modifier = Modifier.size(42.dp),
            tint = Color.White
        )
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
            .width(380.dp)
            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
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
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                Text(
                    text = "January 12, ${movie.yearOfRelease}",
                    modifier = Modifier,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                 LazyRow(
                     modifier = Modifier.padding(end=20.dp)
                 ){
                    items(movie.genres.size){ index ->
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
                                text = movie.genres[index].genreName,
                                modifier = Modifier,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
        Divider(
            modifier = Modifier
                .width(380.dp)
                .padding(top = 10.dp, start = 20.dp, end = 20.dp),
            color = Color(0xFF515151)
        )
        Text(
            text = "Synopsis",
            modifier = Modifier.padding(start=20.dp,top=10.dp),
            fontSize = 16.sp,
            color = Color.White
        )
        ReadMoreText()
        Text(
            text = "Related Movies",
            modifier = Modifier.padding(start=20.dp,top=10.dp),
            fontSize = 16.sp,
            softWrap = true,
            maxLines = 2,
            color = Color.White
        )
        RelatedMoviesCarousel()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RelatedMoviesCarousel(onClick:()->Unit = {}){
    LazyRow(
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        items(dummyMovies.size) { index->
            val movie = dummyMovies[index]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp)
                    .clickableWithoutRipple {
                        onClick()
                    }
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
val summarySample =  """Rey (Daisy Ridley) finally manages to find the legendary Jedi knight, Luke Skywalker (Mark Hamill) on an island with a magical aura. The heroes of The Force Awakens including Leia, Finn"""
@Composable
fun ReadMoreText(synopsis:String=summarySample){

    val minimumLineLength = 3
    var expandedState by remember { mutableStateOf(false) }
    var showReadMoreButtonState by remember { mutableStateOf(false) }
    val maxLines = if (expandedState) 200 else minimumLineLength

    Column(modifier = Modifier.padding(start = 20.dp, end = 30.dp)) {
        Text(
            text = synopsis,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,                   //Make sure to add this line
            maxLines = maxLines,
            onTextLayout = { textLayoutResult: TextLayoutResult ->
                if (textLayoutResult.lineCount > minimumLineLength-1) {           //Adding this check to avoid ArrayIndexOutOfBounds Exception
                    if (textLayoutResult.isLineEllipsized(minimumLineLength-1)) showReadMoreButtonState = true
                }
            }
        )
        if (showReadMoreButtonState) {
            Text(
                text = if (expandedState) "Read Less" else "Read More",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.clickableWithoutRipple {
                    expandedState = !expandedState
                },
            )
        }
    }
}
@Composable
fun CurrentMovieImage(){
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
                .padding(top = 110.dp, start = 145.dp)
                .size(64.dp)
                .background(
                    color = Color.White.copy(0.3f),
                    shape = RoundedCornerShape(50)
                )
                .border(BorderStroke(2.dp, Color.Gray.copy(0.4f)), RoundedCornerShape(50)),
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