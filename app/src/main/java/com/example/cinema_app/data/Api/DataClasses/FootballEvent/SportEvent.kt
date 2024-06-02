package com.example.cinema_app.data.Api.DataClasses.FootballEvent

import java.io.Serializable

data class SportEvent(
    val sportEventName: String,
    val team1Name: String,
    val team1Country: String,
    val team2Name: String,
    val team2Country: String,
    val dateTime: String,
    val team1LogoUrl: String,
    val team2LogoUrl: String,
): Serializable

