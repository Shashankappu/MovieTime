package com.example.movietime.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.extension.clickableWithoutRipple
import com.example.movietime.ui.customcomponents.CustomDropDown
import com.example.movietime.ui.customcomponents.CustomOutlinedTextField
import com.example.movietime.ui.theme.bgPurple
import com.example.movietime.ui.theme.orange
import com.example.movietime.viewmodels.ProfileViewModel

@Composable
fun EditProfileScreen(
    profileViewModel: ProfileViewModel,
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
            CustomDropDown(profileViewModel)
            UserNameField(profileViewModel)
            FirstNameField(profileViewModel)
            LastNameField(profileViewModel)
            UserEmailField(profileViewModel)
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
fun UserNameField(profileViewModel: ProfileViewModel){
    val username by profileViewModel.getUsername().observeAsState("")
    CustomOutlinedTextField(text= username,label = "Username"){
        profileViewModel.setUserName(it)
    }
}

@Composable
fun FirstNameField(profileViewModel: ProfileViewModel){
    val firstname by profileViewModel.getFirstName().observeAsState("")
    CustomOutlinedTextField(text= firstname,label = "Firstname"){
        profileViewModel.setFirstName(it)
    }
}

@Composable
fun LastNameField(profileViewModel: ProfileViewModel){
    val lastname by profileViewModel.getLastName().observeAsState("")
    CustomOutlinedTextField(text= lastname,label = "Lastname"){
        profileViewModel.setLastName(it)
    }
}

@Composable
fun UserEmailField(profileViewModel: ProfileViewModel){
    val userEmail by profileViewModel.getEmail().observeAsState("")
    CustomOutlinedTextField(text= userEmail,label = "Email"){
        profileViewModel.setEmail(it)
    }
}
