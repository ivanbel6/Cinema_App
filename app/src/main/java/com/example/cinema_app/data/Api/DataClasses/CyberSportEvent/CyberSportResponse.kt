package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import java.io.Serializable

data class CyberSportResponse(
    val events: List<CyberSportEvent>
): Serializable
