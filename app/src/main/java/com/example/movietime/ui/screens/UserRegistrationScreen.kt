package com.example.movietime.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.movietime.R
import com.example.movietime.extension.clickableWithoutRipple
import com.example.movietime.ui.customcomponents.CustomOutlinedTextField
import com.example.movietime.ui.theme.bgPurple
import com.example.movietime.ui.theme.orange
import com.example.movietime.viewmodels.ProfileViewModel
import com.example.movietime.viewmodels.UserRegistrationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserRegistrationScreen(
    register: () -> Unit
) {
    val profileViewModel:ProfileViewModel = koinViewModel()
    val userRegistrationViewModel: UserRegistrationViewModel = koinViewModel()
    val context = LocalContext.current.applicationContext
    val loginText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)) {
            append("Already have an account? ")
        }
        withStyle(style = SpanStyle(color = orange)) {
            append("Login")
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().background(bgPurple),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registration",
            fontSize = 30.sp,
            color = orange,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AnimatedPreloader(modifier = Modifier.fillMaxWidth().height(200.dp))
            UsernameFieldForRegistration(profileViewModel,userRegistrationViewModel)
            PasswordFieldForRegistration(profileViewModel,userRegistrationViewModel)
            ConfirmPasswordFieldForRegistration(profileViewModel,userRegistrationViewModel)
            EmailAddressFieldForRegistration(profileViewModel,userRegistrationViewModel)
            Text(loginText, fontSize = 16.sp, modifier = Modifier.padding(top = 10.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .background(orange, RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally)
                .clickableWithoutRipple {
                    if (userRegistrationViewModel.validatePassword()) {
                        register()
                    } else {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text("Register")
        }
    }
}

@Composable
fun UsernameFieldForRegistration(profileViewModel: ProfileViewModel,userRegistrationViewModel: UserRegistrationViewModel){
    val username by userRegistrationViewModel.getUsername().observeAsState("")
    CustomOutlinedTextField(text= username,label = "Username"){
        profileViewModel.setUserName(it)
        userRegistrationViewModel.setUsername(it)
    }
}

@Composable
fun PasswordFieldForRegistration(profileViewModel: ProfileViewModel,userRegistrationViewModel: UserRegistrationViewModel){
    val firstname by userRegistrationViewModel.getPassword().observeAsState("")
    CustomOutlinedTextField(text= firstname,label = "Firstname"){
        profileViewModel.setFirstName(it)
        userRegistrationViewModel.setPassword(it)
    }
}

@Composable
fun ConfirmPasswordFieldForRegistration(profileViewModel: ProfileViewModel,userRegistrationViewModel: UserRegistrationViewModel){
    val lastname by userRegistrationViewModel.getConfirmPassword().observeAsState("")
    CustomOutlinedTextField(text= lastname,label = "Lastname"){
        profileViewModel.setLastName(it)
        userRegistrationViewModel.setConfirmPassword(it)
    }
}

@Composable
fun EmailAddressFieldForRegistration(profileViewModel: ProfileViewModel,userRegistrationViewModel: UserRegistrationViewModel){
    val userEmail by userRegistrationViewModel.getEmail().observeAsState("")
    CustomOutlinedTextField(text= userEmail,label = "Email"){
        profileViewModel.setEmail(it)
        userRegistrationViewModel.setEmail(it)
    }
}

@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.register_lottie_animation
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}