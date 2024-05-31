package com.example.cinema_app.data.Api.DataClasses.VolleyballEvent

import com.example.cinema_app.data.Api.DataClasses.BasketballEvent.Country
import com.example.cinema_app.data.Api.DataClasses.BasketballEvent.League
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.Teams

data class VolleyballEvent(
    val id: Int,
    val date: String,
    val time: String,
    val timestamp: Long,
    val timezone: String,
    val status: Status,
    val country: Country,
    val league: League,
    val teams: Teams,
    val scores: Scores,
    val periods: Periods
)
