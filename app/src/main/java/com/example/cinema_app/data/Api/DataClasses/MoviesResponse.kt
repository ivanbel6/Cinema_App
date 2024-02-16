package com.example.cinema_app.data.Api.DataClasses

data class MoviesResponse(
    val docs: List<Movie>,
    val total: Int,
    val limit: Int,
    val page: Int,
    val pages: Int
)
