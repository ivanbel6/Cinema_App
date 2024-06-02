package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import java.io.Serializable

data class Status(
    val code: Int,
    val description: String,
    val type: String
): Serializable
