package com.example.movietime.ui.customcomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.extension.clickableWithoutRipple
import com.example.movietime.ui.theme.bgPurple
import com.example.movietime.ui.theme.orange
import com.example.movietime.viewmodels.Gender
import com.example.movietime.viewmodels.ProfileViewModel

@Composable
fun CustomDropDown(profileViewModel: ProfileViewModel) {
    var isExpanded by remember { mutableStateOf(false) }
    val selectedItem by profileViewModel.getGender().observeAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(bgPurple, RoundedCornerShape(10.dp))
            .clickableWithoutRipple {
                isExpanded = true
            }
            .border(
                BorderStroke(
                    if (isExpanded) 2.dp else 0.dp,
                    if (isExpanded) orange else Color.Gray
                ), RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedItem.toString(),
                color = Color.White,
                fontSize = 16.sp
            )
            Icon(
                imageVector = if(!isExpanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = "Dropdown Icon",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .size(200.dp, 150.dp)
                .background(bgPurple)
        ) {
            DropdownMenuItem(
                text = { Text("Male", modifier = Modifier.padding(8.dp)) },
                onClick = {
                    profileViewModel.setGender(Gender.MALE)
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Female", modifier = Modifier.padding(8.dp)) },
                onClick = {
                    profileViewModel.setGender(Gender.FEMALE)
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Other", modifier = Modifier.padding(8.dp)) },
                onClick = {
                    profileViewModel.setGender(Gender.OTHERS)
                    isExpanded = false
                }
            )
        }
    }
}