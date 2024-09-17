package com.example.movietime.sharedpreference

import android.util.Log
import com.example.movietime.utils.Constants

class StateManager(private val sharedPreferencesHelper: SharedPreferencesHelper) {

    fun getProfileImageUrl(): String {
        Log.d("Shashank","statemanager get : ${sharedPreferencesHelper.getString(Constants.KEY_PROFILE_IMAGE_URI, Constants.DEFAULT_PROFILE_IMAGE_URI)}")
        return sharedPreferencesHelper.getString(Constants.KEY_PROFILE_IMAGE_URI, Constants.DEFAULT_PROFILE_IMAGE_URI)

    }

    fun setProfileImageUrl(uri: String) {
        sharedPreferencesHelper.putString(Constants.KEY_PROFILE_IMAGE_URI, uri)
        Log.d("Shashank","statemanager : $uri")
    }
}