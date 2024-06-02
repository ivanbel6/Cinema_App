package com.example.cinema_app.data.Api.DataClasses.TvSeries

import com.example.cinema_app.data.Api.DataClasses.Films.Movie

data class SeriesResponse(
    val docs: List<Series>,
    val total: Int,
    val limit: Int,
    val page: Int,
    val pages: Int
)
