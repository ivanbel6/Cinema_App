package com.example.cinema_app.data.Api.DataClasses.FootballEvent

import java.io.Serializable

data class SportEvent(
    val type:String,
    val sportEventName: String,
    val team1Name: String,
    val team1Country: String,
    val team2Name: String,
    val team2Country: String,
    val dateTime: String,
    val team1LogoUrl: String,
    val team2LogoUrl: String,
    val imageUrl:String,
    val team1Score:String,
    val team2Score:String,
    val matchStatus:String,
    val leagueLogoUrl:String,
    val leagueFlagUrl:String,
    val leagueName:String,
    val leagueCountry:String,
    val leagueSeason:String,
    val leagueType:String,

    val firstTimeScore:String?,
    val fullTimeScore:String?,
    val extraTimeScore:String?,
    val penaltyScore:String?,
    val firstPeriodScore:String?,
    val secondPeriodScore:String?,
    val thirdPeriodScore:String?,
    val fourthPeriodScore:String?,
    val fifthPeriodScore:String?
): Serializable

