package com.example.cinema_app.Api.DataClasses

data class Name(
    val name: String,
    val language: String,
    val type: String
)

data class Poster(
    val url: String,
    val previewUrl: String
)

data class Genre(
    val name: String
)

data class Movie(
    val names: List<Name>,
    val description: String?,
    val poster: Poster,
    val genres: List<Genre>
)

data class MoviesResponse(
    val docs: List<Movie>,
    val total: Int,
    val limit: Int,
    val page: Int,
    val pages: Int
)
