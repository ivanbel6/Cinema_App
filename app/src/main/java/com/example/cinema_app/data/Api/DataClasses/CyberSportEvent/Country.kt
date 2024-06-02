package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import java.io.Serializable

data class Country(
    val alpha2: String,
    val alpha3: String,
    val name: String
): Serializable
