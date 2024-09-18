package com.example.movietime.sharedpreference

import android.util.Log
import com.example.movietime.utils.Constants
import com.example.movietime.utils.Constants.DEFAULT_GENDER
import com.example.movietime.utils.Constants.DEFAULT_USER_EMAIL
import com.example.movietime.utils.Constants.DEFAULT_USER_NAME
import com.example.movietime.utils.Constants.KEY_USER_NAME

class StateManager(private val sharedPreferencesHelper: SharedPreferencesHelper) {

    fun getProfileImageUrl(): String {
        Log.d("Shashank","statemanager get : ${sharedPreferencesHelper.getString(Constants.KEY_PROFILE_IMAGE_URI, Constants.DEFAULT_PROFILE_IMAGE_URI)}")
        return sharedPreferencesHelper.getString(Constants.KEY_PROFILE_IMAGE_URI, Constants.DEFAULT_PROFILE_IMAGE_URI)

    }

    fun getUserEmail(): String {
        return sharedPreferencesHelper.getString(Constants.KEY_USER_EMAIL, DEFAULT_USER_EMAIL)
    }

    fun getUserName(): String {
        return sharedPreferencesHelper.getString(KEY_USER_NAME, DEFAULT_USER_NAME)
    }

    fun getGender(): String {
        return sharedPreferencesHelper.getString(Constants.KEY_GENDER, DEFAULT_GENDER)
    }

    fun setUserEmail(email: String) {
        sharedPreferencesHelper.putString(Constants.KEY_USER_EMAIL, email)
    }

    fun setUserName(name: String) {
        sharedPreferencesHelper.putString(KEY_USER_NAME, name)
    }

    fun setGender(gender: String) {
        sharedPreferencesHelper.putString(Constants.KEY_GENDER, gender)
    }

    fun setProfileImageUrl(uri: String) {
        sharedPreferencesHelper.putString(Constants.KEY_PROFILE_IMAGE_URI, uri)
        Log.d("Shashank","statemanager : $uri")
    }
}