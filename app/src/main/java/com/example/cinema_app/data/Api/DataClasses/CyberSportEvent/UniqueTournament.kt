package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import java.io.Serializable

data class UniqueTournament(
    val name: String,
    val slug: String,
    val category: Category,
    val userCount: Int,
    val crowdsourcingEnabled: Boolean,
    val hasPerformanceGraphFeature: Boolean,
    val id: Int,
    val hasEventPlayerStatistics: Boolean,
    val displayInverseHomeAwayTeams: Boolean
): Serializable