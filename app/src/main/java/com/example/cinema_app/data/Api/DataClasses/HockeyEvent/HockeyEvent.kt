package com.example.cinema_app.data.Api.DataClasses.HockeyEvent

import com.example.cinema_app.data.Api.DataClasses.BasketballEvent.Country
import com.example.cinema_app.data.Api.DataClasses.BasketballEvent.League
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.Teams
import com.example.cinema_app.data.Api.DataClasses.VolleyballEvent.Scores
import com.example.cinema_app.data.Api.DataClasses.VolleyballEvent.Status

data class HockeyEvent(
    val id: Int,
    val date: String,
    val time: String,
    val timestamp: Long,
    val timezone: String,
    val week: String?,
    val timer: String?,
    val status: Status,
    val country: Country,
    val league: League,
    val teams: Teams,
    val scores: Scores,
    val periods: Periods,
    val events: Boolean
)
