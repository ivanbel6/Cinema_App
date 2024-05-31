package com.example.cinema_app.data.Api.DataClasses.FootballEvent

data class TeamInfo(
    val id: Int,
    val name: String,
    val logo: String,
    val winner: Any? // Может быть null или Boolean, в зависимости от данных
)
