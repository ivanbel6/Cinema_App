package com.example.cinema_app.data.Api.DataClasses

import java.io.Serializable


data class CustomDataClass(
    val bgImage: Poster,
    val name: String,
    val date: Int,
    val time: String,
    val Genre: String,
    val description :String,
    val backdrop: Backdrop,
    val persons: List<Person>,
    val videos: Map<String, List<Video>>,

    val ageRating: Int,
    val countries: List<Country>,
    val premiere: Premiere,
//    val releaseYears: List<ReleaseYear>,

):Serializable
