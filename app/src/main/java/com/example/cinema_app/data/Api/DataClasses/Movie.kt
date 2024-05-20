package com.example.cinema_app.data.Api.DataClasses

import java.io.Serializable

data class Movie(
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
    val movieLength: Int,
    val backdrop: Backdrop,
    val persons: List<Person>,
    val videos: Map<String, List<Video>>

)
data class Person(
    val id: Int,
    val photo: String,
    val name: String?,
    val enName: String,
    val description: String?,
    val profession: String,
    val enProfession: String
):Serializable

data class Video(
    val url: String,
    val name: String,
    val site: String,
    val type: String
):Serializable

data class Backdrop(
    val url: String,
    val previewUrl: String
):Serializable
data class Country(
    val name: String
):Serializable

data class Premiere(
    val world: String,
    val russia: String
):Serializable

data class ReleaseYear(
    val start: Int,
    val end: Int
):Serializable

