package com.example.movietime.model

data class Movie(
    val id:Long,
    val name:String,
    val runtime:Int,
    val rating:Float ,
    val genres:List<String>,
    val isAdultRated:Boolean
)
