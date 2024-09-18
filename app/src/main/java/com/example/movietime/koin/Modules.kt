package com.example.movietime.koin

import com.example.movietime.movieservice.MovieService
import com.example.movietime.sharedpreference.SharedPreferencesHelper
import com.example.movietime.sharedpreference.StateManager
import com.example.movietime.viewmodels.MainViewModel
import com.example.movietime.viewmodels.ProfileViewModel
import com.example.movietime.viewmodels.UserRegistrationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule  = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.4:8082/api/v1/")  //replace with actual IP and port
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MovieService> {
        get<Retrofit>().create(MovieService::class.java)
    }
    single { SharedPreferencesHelper(androidContext()) }
    single { StateManager(get()) }

    // Inject StateManager into ProfileViewModel
    viewModel { ProfileViewModel(get(),get()) }
    viewModel { MainViewModel(get()) }
    viewModel { UserRegistrationViewModel() }
}