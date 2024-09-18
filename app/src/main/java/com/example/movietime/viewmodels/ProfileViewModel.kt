package com.example.movietime.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietime.model.Genre
import com.example.movietime.sharedpreference.StateManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val stateManager: StateManager) : ViewModel() {
    private val genres = listOf(
        Genre(1, "Family"),
        Genre(2, "Drama"),
        Genre(3, "Action"),
        Genre(4, "Horror")
    )
    private val _gender = MutableLiveData(stateManager.getGender())
    private val _username = MutableLiveData(stateManager.getUserName())
    private val _firstname = MutableLiveData("Shashank")
    private val _lastname = MutableLiveData("S P")
    private val _email = MutableLiveData(stateManager.getUserEmail())
    private val _profileImageUrl = MutableStateFlow(stateManager.getProfileImageUrl())
    private val _favouriteGenresList = MutableLiveData(genres)

    fun getFavouriteGenresList() : LiveData<List<Genre>> = _favouriteGenresList
    fun getGender() : LiveData<String> = _gender
    fun getUsername() : LiveData<String> = _username
    fun getFirstName() : LiveData<String> = _firstname
    fun getLastName() : LiveData<String> = _lastname
    fun getEmail() : LiveData<String> = _email
    fun getProfileImageUrl() : StateFlow<String> = _profileImageUrl

    fun setGender(gender: String){
        _gender.postValue(gender)
        viewModelScope.launch {
            stateManager.setGender(gender)
        }
    }

    fun setUserName(username: String){
        _username.postValue(username)
        viewModelScope.launch {
            stateManager.setUserName(username)
        }
    }

    fun setFirstName(firstName: String){
        _firstname.postValue(firstName)
    }

    fun setLastName(lastName: String){
        _lastname.postValue(lastName)
    }

    fun setEmail(email: String){
        _email.postValue(email)
        viewModelScope.launch {
            stateManager.setUserEmail(email)
        }
    }

    fun setProfileImageUrl(imageUrl: String){
        _profileImageUrl.value = imageUrl
        viewModelScope.launch {
            Log.d("Shashank","Profile Image Url : $imageUrl")
            stateManager.setProfileImageUrl(imageUrl)
        }
    }

    fun setFavouriteGenresList(genres : List<Genre>){
        _favouriteGenresList.postValue(genres)
    }

}