package com.example.movietime.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movietime.R
import com.example.movietime.extension.clickableWithoutRipple
import com.example.movietime.model.Movie
import com.example.movietime.ui.theme.orange
import com.example.movietime.utils.GENRE
import com.example.movietime.utils.dummyMovies
import com.example.movietime.viewmodels.MainViewModel
import kotlin.enums.EnumEntries


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchRoute(
    mainViewModel: MainViewModel,
    onMovieItemClick: (movieData: Movie) -> Unit
){
    SearchScreen(
        mainViewModel = mainViewModel,
        onMovieItemClick = onMovieItemClick
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchScreen(
    mainViewModel: MainViewModel,
    onMovieItemClick: (movieData: Movie) -> Unit
) {
    val searchQuery by mainViewModel.getQuery().collectAsState()
    val moviesList by mainViewModel.searchedMoviesList.collectAsState(dummyMovies)
    val selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = GENRE.entries.toList()

    LaunchedEffect(Unit) {
        if (moviesList.isEmpty()) {
            if(selectedTabIndex!=0) {
                mainViewModel.fetchMoviesBySearchQuery("", tabTitles[selectedTabIndex].name)
            }
            else mainViewModel.fetchMoviesBySearchQuery("",null)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.title_text_find_movies),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 36.sp,
            modifier = Modifier
                .padding(top = 20.dp, start = 24.dp)
                .size(283.dp, 72.dp)
        )
        SearchBox(
            searchQuery = searchQuery,
            onSearchQueryChanged = {
                mainViewModel.setQuery(it)
                mainViewModel.fetchMoviesBySearchQuery(it,null)
            }

        )
        GenreTabLayout(
            selectedTabIndex = selectedTabIndex,
            genreList = GENRE.entries,
            onTabSelection = { index ->
                if(selectedTabIndex == GENRE.ALL.id)
                    mainViewModel.fetchMoviesBySearchQuery(searchQuery,null)
                else
                    mainViewModel.fetchMoviesBySearchQuery(searchQuery,tabTitles[index].name)
            }
        )
        StaggeredMovieLayout(moviesList = moviesList, onMovieItemClick = onMovieItemClick)
    }
}

@Composable
fun SearchBox(
    searchQuery: String,
    onSearchQueryChanged: (query: String) -> Unit
){
    TextField(
        value = searchQuery,
        onValueChange = {
            onSearchQueryChanged(it)
        },
        placeholder = {
            Text(
                text = stringResource(R.string.text_search),
                color = Color.Gray,
                fontSize = 16.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
        enabled = true,
        textStyle = TextStyle(textAlign = TextAlign.Left, fontSize = 16.sp),
        modifier = Modifier
            .padding(start = 20.dp, bottom = 15.dp)
            .size(328.dp, 50.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(10)),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
fun GenreTabLayout(
    selectedTabIndex:Int,
    genreList: EnumEntries<GENRE>,
    onTabSelection :(Int) -> Unit
){
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .padding(top = 10.dp)
            .height(50.dp)
            .fillMaxWidth(),
        edgePadding = 10.dp,
        divider = {   },
        containerColor = Color.Transparent,
        contentColor = Color.White,
        indicator = { tabPositions->
            TabRowDefaults.PrimaryIndicator(
                Modifier
                    .padding(2.dp)
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .background(Color.Transparent),
                color = orange,
                height = 2.dp
            )
        }
    ) {
        genreList.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelection(index)
                },
                text = {
                    Text(
                        text = item.name,
                        color = if (selectedTabIndex == index) orange else Color.White,
                        fontSize = 16.sp,
                        maxLines = 1,
                    )
                }
            )
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StaggeredMovieLayout(
    moviesList : List<Movie>,
    onMovieItemClick : (movieData:Movie) -> Unit
){
    if(moviesList.isNotEmpty()) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(moviesList.size) { index ->
                val movie = moviesList[index]
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .clickableWithoutRipple {
                            onMovieItemClick(movie)
                        }
                ) {
                    val height = if (index % 2 == 0) 184.dp else 160.dp
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
                            .height(height)
                            .width(154.dp),
                    )
                    Text(
                        text = "${movie.title} (${movie.releaseDate})",
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
        }
    } else {
        Image(
            painter = painterResource(id = R.drawable.no_movies_found_img),
            contentDescription ="no movies found",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}