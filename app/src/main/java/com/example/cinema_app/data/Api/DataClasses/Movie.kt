package com.example.cinema_app.data.Api.DataClasses

data class Movie(
    val names: List<Name>,
    val description: String?,
    val poster: Poster,
    val genres: List<Genre>,
    val rating: Rating?,
    val videos: Videos?
)
