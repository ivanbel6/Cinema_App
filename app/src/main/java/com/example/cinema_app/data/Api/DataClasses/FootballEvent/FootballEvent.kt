package com.example.cinema_app.data.Api.DataClasses.FootballEvent

data class FootballEvent(
    val fixture: Fixture,
    val leaque: League,
    val teams: Teams,
    val goals: Goals,
    val score: Score
)
