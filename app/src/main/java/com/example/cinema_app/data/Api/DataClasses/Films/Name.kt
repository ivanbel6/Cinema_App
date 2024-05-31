package com.example.cinema_app.data.Api.DataClasses.Films

data class Name(
    val name: String,
    val language: String = "default_language",
    val type: String = "default_type"
)

