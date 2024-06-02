package com.example.cinema_app.data.Api.DataClasses.TvSeries

import com.example.cinema_app.data.Api.DataClasses.Films.Backdrop
import com.example.cinema_app.data.Api.DataClasses.Films.Country
import com.example.cinema_app.data.Api.DataClasses.Films.Genre
import com.example.cinema_app.data.Api.DataClasses.Films.Person
import com.example.cinema_app.data.Api.DataClasses.Films.Poster
import com.example.cinema_app.data.Api.DataClasses.Films.Premiere
import com.example.cinema_app.data.Api.DataClasses.Films.Rating
import com.example.cinema_app.data.Api.DataClasses.Films.Video
import java.io.Serializable

data class Series(
    val name: String,

    val ageRating: Int,
    val countries: List<Country>,
    val premiere: Premiere,
//    val releaseYears: List<ReleaseYear>,

    val year: Int,
    val description: String,
    val poster: Poster,
    val genres: List<Genre>,
    val rating: Rating?,
    val seriesLength: Int,
    val backdrop: Backdrop,
    val persons: List<Person>,
    val videos: Map<String, List<Video>>
)

