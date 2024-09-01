package com.example.movietime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movietime.model.Genre

enum class Gender(val id : Int){
    MALE(0),FEMALE(1),OTHERS(2)
}
class ProfileViewModel : ViewModel() {
    private val genres = listOf(
        Genre(1, "Family"),
        Genre(2, "Drama"),
        Genre(3, "Action"),
        Genre(4, "Horror")
    )
    private val _gender = MutableLiveData(Gender.MALE)
    private val _username = MutableLiveData("Shashank")
    private val _firstname = MutableLiveData("Shashank")
    private val _lastname = MutableLiveData("S P")
    private val _email = MutableLiveData("shashanksp1512@gmail.com")
    private val _profileImageUrl = MutableLiveData("https://media.sproutsocial.com/uploads/2022/06/profile-picture.jpeg")
    private val _favouriteGenresList = MutableLiveData(genres)

    fun getFavouriteGenresList() : LiveData<List<Genre>> = _favouriteGenresList
    fun getGender() : LiveData<Gender> = _gender
    fun getUsername() : LiveData<String> = _username
    fun getFirstName() : LiveData<String> = _firstname
    fun getLastName() : LiveData<String> = _lastname
    fun getEmail() : LiveData<String> = _email
    fun getProfileImageUrl() : LiveData<String> = _profileImageUrl

    fun setGender(gender: Gender){
        _gender.postValue(gender)
    }

    fun setUserName(username: String){
        _username.postValue(username)
    }

    fun setFirstName(firstName: String){
        _firstname.postValue(firstName)
    }

    fun setLastName(lastName: String){
        _lastname.postValue(lastName)
    }

    fun setEmail(email: String){
        _email.postValue(email)
    }

    fun setProfileImageUrl(imageUrl: String){
        _email.postValue(imageUrl)
    }

    fun setFavouriteGenresList(genres : List<Genre>){
        _favouriteGenresList.postValue(genres)
    }

}