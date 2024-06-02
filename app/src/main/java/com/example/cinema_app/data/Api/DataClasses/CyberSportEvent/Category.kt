package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import java.io.Serializable

data class Category (
    val name: String,
    val slug: String,
    val sport: Sport,
    val id: Int,
    val flag: String
): Serializable
