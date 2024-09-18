package com.example.movietime.ui.screens

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.movietime.R
import com.example.movietime.extension.clickableWithoutRipple
import com.example.movietime.model.Movie
import com.example.movietime.ui.theme.orange
import com.example.movietime.utils.dummyMovies
import com.example.movietime.viewmodels.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel,onEditProfileClicked:()->Unit) {
    val genres by profileViewModel.getFavouriteGenresList().observeAsState()
    val username by profileViewModel.getUsername().observeAsState("")
    val userEmail by profileViewModel.getEmail().observeAsState("")

    LaunchedEffect(Unit) {
        profileViewModel.fetchMovieByMultipleGenres(genres!!)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            EditProfileImage(profileViewModel)
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = username,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    fontSize = 18.sp
                )
                Text(
                    text = userEmail,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    Text(
                        text = "Edit profile",
                        color = Color.White,
                        modifier = Modifier
                            .clickableWithoutRipple {
                                onEditProfileClicked()
                            },
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Icon",
                        modifier = Modifier.size(12.dp),
                        tint = Color.White)
                }
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
            var times = genres?.size?:0
            repeat(times) {
                times--
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .wrapContentSize()
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
                    genres?.get(times)?.let { it1 ->
                        Text(
                            text = it1.genreName,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier.padding(top = 10.dp)
        ){
            Text(
                text = "Saved Movies:",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 24.sp
            )
            HorizontalMoviesCarousel()
            Text(
                text = "Recommended Movies :",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 24.sp
            )
            HorizontalMoviesCarousel()
            Text(
                text = "Your Favourites:",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 24.sp
            )
            val favouriteMoviesList by profileViewModel.favouritesMoviesList.collectAsState()
            HorizontalMoviesCarousel(favouriteMoviesList)
        }
    }
}

@Composable
fun EditProfileImage(
    profileViewModel: ProfileViewModel
){
    val imageUri by profileViewModel.getProfileImageUrl().collectAsState()
    val context = LocalContext.current
    LaunchedEffect(imageUri) {
        profileViewModel.setProfileImageUrl(imageUri)
    }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            profileViewModel.setProfileImageUrl(it.toString())
        }
    }
    Box(
        modifier = Modifier.padding(20.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .crossfade(true)
                .diskCachePolicy(CachePolicy.DISABLED) // Disable disk cache temporarily
                .build(),
            placeholder = painterResource(R.drawable.godfather),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(50))
                .border(
                    border = BorderStroke(2.dp, Color.Gray),
                    shape = RoundedCornerShape(50)
                )
        )
        Box(
            modifier = Modifier.align(Alignment.BottomEnd)
        ){
            Image(
                imageVector = Icons.Default.Edit,
                contentDescription = "edit profile",
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(50))
                    .border(
                        border = BorderStroke(2.dp, Color.Gray.copy(0.4f)),
                        shape = RoundedCornerShape(50)
                    )
                    .background(orange)
                    .clickableWithoutRipple {
                        imagePickerLauncher.launch("image/*")
                    },
                contentScale = ContentScale.Inside
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorizontalMoviesCarousel(moviesList:List<Movie> = dummyMovies, onClick:()->Unit = {}){
    LazyRow(
        modifier = Modifier
            .padding(top = 10.dp, start = 5.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        items(moviesList.size) { index->
            val movie = moviesList[index]
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
                    text = movie.title,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    maxLines = 1,
                    lineHeight = 14.sp
                )
                Text(
                    text = "${movie.releaseDate})",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    maxLines = 1,
                    lineHeight = 14.sp
                )
            }
        }
    }
}
