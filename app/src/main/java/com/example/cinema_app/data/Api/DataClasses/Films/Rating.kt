package com.example.cinema_app.data.Api.DataClasses.Films

data class Rating(
    val tmdb: Double?,
    val imdb: Double?,
    val filmCritics: Double?,
    val russianFilmCritics: Double?,
    val await: Double?
)
