package com.example.cinema_app.domain.UseCases

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SportEvent
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.adapters.SportsRecycleAdapter
import com.example.cinema_app.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class CreateHockeySlider {
    fun fill(hockeyRecyclerView: RecyclerView, applicationContext: Context) {
        hockeyRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val sportsApiInterface = MainActivity.hockeyretrofit.create(ApiInterface::class.java)
        val hockeyList: MutableList<SportEvent> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val hockeyResponseList = sportsApiInterface.getHockeyResponse()
            Log.d("test", hockeyResponseList.toString())
            for (el in hockeyResponseList.response) {
                val instant = Instant.ofEpochSecond(el.timestamp)
                val dateTimeInstant = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm") // Define your desired date format here
                val dateTime = dateTimeInstant.format(formatter)

                if(el.teams.home.name == "Lada" || el.teams.away.name == "Lada" || el.teams.home.name == "Dynamo Moscow" || el.teams.home.name == "Dynamo Moscow"|| el.teams.home.name == "Vityaz Balashikha" || el.teams.away.name == "Vityaz Balashikha")
                    continue
                else
                    hockeyList.add(
                        SportEvent(
                            type = "хоккей",
                            sportEventName = "Матч регулярного чемпионата КХЛ",
                            imageUrl = "https://dynamo.ru/upload/20181211/FSB16177.JPG",
                            matchStatus = el.status.long,
                            leagueLogoUrl = el.league.logo,
                            leagueFlagUrl = el.country.flag,
                            leagueName = el.league.name,
                            leagueCountry = el.country.name,
                            leagueSeason = el.league.season.toString(),
                            leagueType = "Cup",
                            team1Name = el.teams.home.name,
                            team1Country = el.country.name,
                            team2Name = el.teams.away.name,
                            team2Country = el.country.name,
                            dateTime = dateTime,
                            team1LogoUrl = el.teams.home.logo,
                            team1Score = el.scores.home.toString(),
                            team2LogoUrl = el.teams.away.logo,
                            team2Score = el.scores.away.toString(),
                            firstTimeScore = null,
                            fullTimeScore = null,
                            extraTimeScore = el.periods.overtime,
                            penaltyScore = el.periods.penalties,
                            firstPeriodScore = el.periods.first,
                            secondPeriodScore = el.periods.second,
                            thirdPeriodScore = el.periods.third,
                            fourthPeriodScore = null,
                            fifthPeriodScore = null
                        )
                    )
            }
            val hockeyAdapter = SportsRecycleAdapter(hockeyList)
            hockeyRecyclerView.adapter = hockeyAdapter

//            val customSnapHelper: SnapHelper = PagerSnapHelper()
//            customSnapHelper.attachToRecyclerView(customRecyclerView)
        }
    }
}