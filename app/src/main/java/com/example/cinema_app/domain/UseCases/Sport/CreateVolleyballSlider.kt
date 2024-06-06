package com.example.cinema_app.domain.UseCases

import android.content.Context
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

class CreateVolleyballSlider {
    fun fill(volleyballRecyclerView: RecyclerView, applicationContext: Context) {
        volleyballRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val sportsApiInterface = MainActivity.volleyballretrofit.create(ApiInterface::class.java)
        val volleyballList: MutableList<SportEvent> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val volleyballResponseList = sportsApiInterface.getVolleyballResponse()
            for (el in volleyballResponseList.response) {
                var fourthPeriodScore:String? = null
                var fifthPeriodScore:String? = null

                val instant = Instant.ofEpochSecond(el.timestamp)
                val dateTimeInstant = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm") // Define your desired date format here
                val dateTime = dateTimeInstant.format(formatter)

                if(el.periods.fourth.home != null)
                    fourthPeriodScore = el.periods.fourth.home.toString() +"-"+ el.periods.fourth.away.toString()
                if(el.periods.fifth.home != null)
                    fifthPeriodScore = el.periods.fifth.home.toString() +"-"+ el.periods.fifth.away.toString()

                if(el.teams.home.name != "Orenburg" && el.teams.away.name != "Orenburg")
                   volleyballList.add(
                        SportEvent(
                            type = "волейбол",
                            sportEventName = "Матч регулярного чемпионата по воллейболу среди мужчин",
                            imageUrl = "https://bilook.ru/storage/images/16436431360.png",
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
                            extraTimeScore = null,
                            penaltyScore = null,
                            firstPeriodScore = el.periods.first.home.toString() + "-" + el.periods.first.away.toString(),
                            secondPeriodScore = el.periods.second.home.toString() + "-" + el.periods.second.away.toString(),
                            thirdPeriodScore = el.periods.third.home.toString() + "-" + el.periods.third.away.toString(),
                            fourthPeriodScore = fourthPeriodScore,
                            fifthPeriodScore = fifthPeriodScore
                        )
                    )
            }
            val volleyballAdapter = SportsRecycleAdapter(volleyballList)
            volleyballRecyclerView.adapter = volleyballAdapter

//            val customSnapHelper: SnapHelper = PagerSnapHelper()
//            customSnapHelper.attachToRecyclerView(customRecyclerView)
        }
    }
}