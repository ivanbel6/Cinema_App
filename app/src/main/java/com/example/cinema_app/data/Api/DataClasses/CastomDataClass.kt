package com.example.cinema_app.data.Api.DataClasses

import com.example.cinema_app.data.Api.DataClasses.Genre
import com.example.cinema_app.data.Api.DataClasses.Poster


data class CustomDataClass(
    val bgImage: Poster,
    val title: String,
    val description: String?,
    val Rating: Double,
    val Genre: List<Genre>
)
