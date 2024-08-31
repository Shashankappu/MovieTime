package com.example.movietime.ui.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.R
import com.example.movietime.extension.clickableWithoutRipple
import com.example.movietime.ui.theme.bgPurple
import com.example.movietime.ui.theme.orange


@Composable
fun EditProfileScreen(
    onSubmit:()->Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
            .border(BorderStroke(1.dp, orange), RoundedCornerShape(12.dp))
            .background(bgPurple, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Edit Profile",
            fontSize = 20.sp,
            color = orange
        )

        Column(
            modifier = Modifier
                .wrapContentSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier.padding(20.dp)
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
                            },
                        contentScale = ContentScale.Inside
                    )
                }
            }
            CustomDropDown()
            CustomOutlinedTextField("Username")
            CustomOutlinedTextField("First Name")
            CustomOutlinedTextField("Last Name")
            CustomOutlinedTextField("Email")
        }


        Box(
            modifier = Modifier
                .size(100.dp, 40.dp)
                .background(orange, RoundedCornerShape(20.dp))
                .align(Alignment.End)
                .clickableWithoutRipple {
                    onSubmit()
                },
            contentAlignment = Alignment.Center
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun CustomOutlinedTextField(label :String,keyboardType: KeyboardType = KeyboardType.Text){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Color.White,
            focusedTextColor = Color.White, // Text color when the field is focused
            unfocusedTextColor = Color.Gray, // Text color when the field is unfocused
            focusedIndicatorColor = orange, // Border color when focused
            unfocusedIndicatorColor = Color.Gray, // Border color when unfocused
            cursorColor = Color.White, // Cursor color
            focusedContainerColor = bgPurple,
            unfocusedContainerColor = bgPurple
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        textStyle = TextStyle(textAlign = TextAlign.Left, fontSize = 16.sp),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(bgPurple, RoundedCornerShape(10.dp))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(label :String){
    val datePickerState = rememberDatePickerState()
    DatePicker(
        state = datePickerState,
        modifier = Modifier,
        showModeToggle = false,
        colors = DatePickerDefaults.colors()
    )
}

@Composable
fun CustomDropDown() {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Select Gender") }

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
                text = selectedItem,
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
                    selectedItem = "Male"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Female", modifier = Modifier.padding(8.dp)) },
                onClick = {
                    selectedItem = "Female"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Other", modifier = Modifier.padding(8.dp)) },
                onClick = {
                    selectedItem = "Other"
                    isExpanded = false
                }
            )
        }
    }
}

private fun saveData(email: String,lastname:String,firstname:String,username:String){

}