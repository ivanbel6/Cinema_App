package com.example.cinema_app.data.Api.DataClasses.FootballEvent

data class Fixture(
    val id: Int,
    val referee: String,
    val timezone: String,
    val date: String,
    val timestamp: Long,
    val periods: Periods,
    val venue: Venue,
    val status: Status
)

