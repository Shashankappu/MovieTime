package com.example.movietime.utils

import com.example.movietime.movieservice.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api : MovieService by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost_ip_address:port/")  //replace with actual ip and port
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }

}