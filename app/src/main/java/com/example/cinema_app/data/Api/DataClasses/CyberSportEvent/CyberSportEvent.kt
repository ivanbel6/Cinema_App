package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import java.io.Serializable

data class CyberSportEvent (
    val tournament: Tournament,
    val season: Season,
    val customId: String,
    val status: Status,
    val winnerCode: Int,
    val homeTeam: Team,
    val awayTeam: Team,
    val homeScore: Score,
    val awayScore: Score,
    val coverage: Int,
    val time: Any,
    val changes: Any,
    val hasGlobalHighlights: Boolean,
    val crowdsourcingDataDisplayEnabled: Boolean,
    val id: Int,
    val bestOf: Int,
    val eventType: String,
    val roundsInAHalf: Int,
    val startTimestamp: Long,
    val slug: String,
    val finalResultOnly: Boolean,
    val feedLocked: Boolean,
    val isEditor: Boolean,
    val crowdsourcingEnabled: Boolean
): Serializable