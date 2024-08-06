package com.example.movietime.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.ui.theme.orange

@OptIn(ExperimentalMaterial3Api::class)
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
                .padding(top = 36.dp, start = 24.dp)
                .size(283.dp, 72.dp)
        )
        SearchBox()
        SearchTabs()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTabs(){
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val tabTitles = listOf("Family","Comedy", "Adventure","Drama")
    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp)
            .fillMaxWidth()
            .height(30.dp),
        containerColor = Color.Transparent,
        contentColor = Color.White,
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                Modifier
                    .tabIndicatorOffset(selectedTabIndex, false)
                    .width(100.dp)
                    .background(Color.Transparent),
                color = orange,
                height = 1.dp
            )
        }
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                modifier = Modifier
                    .padding(horizontal = 2.dp),
                text = {
                    Text(
                        text = title,
                        color = if (selectedTabIndex == index) orange else Color.White,
                        fontSize = 16.sp
                    )
                }
            )
        }
    }
}