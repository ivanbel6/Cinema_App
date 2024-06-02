package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import java.io.Serializable

data class Team(
    val name: String,
    val slug: String,
    val shortName: String,
    val sport: Sport,
    val userCount: Int,
    val nameCode: String,
    val national: Boolean,
    val type: Int,
    val id: Int,
    val country: Country,
    val subTeams: List<Any>,
    val teamColors: TeamColors
): Serializable
