package com.example.cinema_app.data.Api.DataClasses.BasketballEvent

data class TeamScores(
    val quarter_1: Int,
    val quarter_2: Int,
    val quarter_3: Int,
    val quarter_4: Int,
    val over_time: Int?,
    val total: Int
)
