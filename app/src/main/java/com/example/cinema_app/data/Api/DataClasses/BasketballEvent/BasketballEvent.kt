package com.example.cinema_app.data.Api.DataClasses.BasketballEvent

import com.example.cinema_app.data.Api.DataClasses.FootballEvent.Teams

data class BasketballEvent(
    val id: Int,
    val date: String,
    val time: String,
    val timestamp: Long,
    val timezone: String,
    val stage: String?,
    val week: String?,
    val status: Status,
    val league: League,
    val country: Country,
    val teams: Teams,
    val scores: Scores
)
