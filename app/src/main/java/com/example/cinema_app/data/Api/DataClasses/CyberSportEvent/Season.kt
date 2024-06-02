package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import java.io.Serializable

data class Season(
    val name: String,
    val year: String,
    val editor: Boolean,
    val id: Int
): Serializable