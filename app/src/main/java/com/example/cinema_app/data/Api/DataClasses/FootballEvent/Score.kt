package com.example.cinema_app.data.Api.DataClasses.FootballEvent

data class Score(
    val halftime: HalfTimeScore,
    val fulltime: FullTimeScore,
    val extratime: ExtraTimeScore,
    val penalty: PenaltyScore
)