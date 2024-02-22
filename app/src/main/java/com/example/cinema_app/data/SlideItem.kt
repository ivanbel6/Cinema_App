package com.example.cinema_app.data

import com.example.cinema_app.data.Api.DataClasses.Poster

data class SlideItem(
    var date: Int,
    var genres: String,
    var imdbRating: Double,
    var tomatoesRating: Double,
    var mainImage: Poster
)