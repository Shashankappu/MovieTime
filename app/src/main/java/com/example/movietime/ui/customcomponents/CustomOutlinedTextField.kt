package com.example.movietime.ui.customcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movietime.ui.theme.bgPurple
import com.example.movietime.ui.theme.orange

@Composable
fun CustomOutlinedTextField(
    text :String, label :String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange:(String)->Unit
){
    val keyboardController = LocalSoftwareKeyboardController.current
    var newText by remember { mutableStateOf(text) }
    OutlinedTextField(
        value = newText,
        onValueChange = {
            newText = it
            onValueChange(it)
        },
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
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }) ,
        textStyle = TextStyle(textAlign = TextAlign.Left, fontSize = 16.sp),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(bgPurple, RoundedCornerShape(10.dp))
    )
}