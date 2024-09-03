package com.example.movietime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserRegistrationViewModel : ViewModel(){

    private val _username = MutableLiveData("")
    private val _email = MutableLiveData("")
    private val _password = MutableLiveData("")
    private val _confirmPassword = MutableLiveData("")

    fun getUsername() : LiveData<String> = _username
    fun getEmail() : LiveData<String> = _email
    fun getPassword() : LiveData<String> = _password
    fun getConfirmPassword() : LiveData<String> = _confirmPassword

    fun setUsername(username : String){
        _username.postValue(username)
    }

    fun setEmail(email : String){
        _email.postValue(email)
    }

    fun setPassword(password : String){
        _password.postValue(password)
    }

    fun setConfirmPassword(confirmPassword : String){
        _confirmPassword.postValue(confirmPassword)
    }

    fun validatePassword() : Boolean{
        return _password.value == _confirmPassword.value
    }
}
