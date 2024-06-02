package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import java.io.Serializable

data class Tournament (
    val name: String,
    val slug: String,
    val category: Category,
    val uniqueTournament: UniqueTournament,
    val priority: Int,
    val id: Int
): Serializable
