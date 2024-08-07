package com.example.movietime.ui.screens

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
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.ui.theme.orange

@Composable
fun SearchScreen() {
    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize(),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Find Movies, Tv series,\nand more..",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 36.sp,
            modifier = Modifier
                .padding(top = 20.dp, start = 24.dp)
                .size(283.dp, 72.dp)
        )
        SearchBox()
        GenreRecommendationTabLayout()
        StaggeredMovieLayout()
    }
}

@Composable
fun SearchBox(){
    var query by remember {
        mutableStateOf("")
    }
    TextField(
        value = query,
        onValueChange = { query = it },
        placeholder = { Text(text = "Search", color = Color.Gray, fontSize = 16.sp) },
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
            .padding(start = 20.dp, top = 10.dp)
            .size(328.dp, 48.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Gray
        )
    )
}

@Composable
fun GenreRecommendationTabLayout(){
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val tabTitles = listOf("Family","Comedy", "Adventure","Drama")
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
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = {
                    Text(
                        text = title,
                        color = if (selectedTabIndex == index) orange else Color.White,
                        fontSize = 16.sp,
                        maxLines = 1,
                    )
                }
            )
        }
    }
}
@Composable
fun StaggeredMovieLayout(){
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(moviesData.size) { index->
            val movie = moviesData[index]
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                val height = if(index%2==0) 184.dp else 160.dp
                Image(
                    painter = painterResource(id = movie.image),
                    contentDescription = "movie image",
                    modifier = Modifier
                        .clip(RoundedCornerShape(20))
                        .height(height)
                        .width(154.dp),
                    contentScale = ContentScale.Crop
                )
                Text(text = "${movie.title} (${movie.yearOfRelease})", modifier = Modifier.padding(top = 12.dp))
            }
        }
    }
}