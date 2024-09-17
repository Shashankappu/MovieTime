package com.example.movietime.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.movietime.utils.Constants

class SharedPreferencesHelper(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)

    /*
        Generic Read And Write Methods
     */
    fun getString(key: String, defaultValue: String = ""): String {
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    fun putString(key: String, value: String) {
        prefs.edit { putString(key, value) }
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return prefs.getInt(key, defaultValue) ?: defaultValue
    }

    fun putInt(key: String, value: Int) {
        prefs.edit { putInt(key, value) }
    }

    fun getBoolean(key: String,defaultValue: Boolean):Boolean{
        return prefs.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String,value: Boolean){
        prefs.edit { putBoolean(key,value)}
    }

}
