package com.example.movietime.koin

import com.example.movietime.movieservice.MovieService
import com.example.movietime.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule  = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.10:8082/api/v1/")  //replace with actual IP and port
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MovieService> {
        get<Retrofit>().create(MovieService::class.java)
    }

    viewModel { MainViewModel(get()) }
}