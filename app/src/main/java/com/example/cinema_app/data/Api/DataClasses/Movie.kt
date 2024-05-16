package com.example.cinema_app.data.Api.DataClasses

data class Movie(
    val name: String,
    val year: Int,
    val description: String?,
    val poster: Poster,
    val genres: List<Genre>,
    val rating: Rating?,
    val videos: Videos?
)
