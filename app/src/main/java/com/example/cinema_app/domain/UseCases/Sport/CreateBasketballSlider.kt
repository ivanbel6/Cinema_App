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


class CreateBasketballSlider {
    fun fill(basketballRecyclerView: RecyclerView, applicationContext: Context) {
        basketballRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val sportsApiInterface = MainActivity.basketballretrofit.create(ApiInterface::class.java)
        val basketballList: MutableList<SportEvent> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val basketballResponseList = sportsApiInterface.getBasketballResponse()
            for (el in basketballResponseList.response) {
                var extraTimeScore:String? = null

                val instant = Instant.ofEpochSecond(el.timestamp)
                val dateTimeInstant = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm") // Define your desired date format here
                val dateTime = dateTimeInstant.format(formatter)


                if(el.scores.home.over_time != null)
                    extraTimeScore = el.scores.home.over_time.toString() + "-" + el.scores.away.over_time.toString()

                basketballList.add(
                    SportEvent(
                        type = "баскетбол",
                        sportEventName = "Матч регулярного чемпионата NBA",
                        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/%C3%9Clker_Sports_Arena-_Fenerbah%C3%A7e_vs._Armani_Milano.jpg/1200px-%C3%9Clker_Sports_Arena-_Fenerbah%C3%A7e_vs._Armani_Milano.jpg",
                        matchStatus = el.status.long,
                        leagueLogoUrl = el.league.logo,
                        leagueFlagUrl = el.country.flag,
                        leagueName = el.league.name,
                        leagueCountry = el.country.name,
                        leagueSeason = el.league.season.toString(),
                        leagueType = "League",
                        team1Name = el.teams.home.name,
                        team1Country = el.country.name,
                        team2Name = el.teams.away.name,
                        team2Country = el.country.name,
                        dateTime = dateTime,
                        team1LogoUrl = el.teams.home.logo,
                        team1Score = el.scores.home.total.toString(),
                        team2LogoUrl = el.teams.away.logo,
                        team2Score = el.scores.away.total.toString(),
                        firstTimeScore = null,
                        fullTimeScore = null,
                        extraTimeScore = extraTimeScore,
                        penaltyScore = null,
                        firstPeriodScore = el.scores.home.quarter_1.toString() + "-" + el.scores.away.quarter_1.toString(),
                        secondPeriodScore = el.scores.home.quarter_2.toString() + "-" + el.scores.away.quarter_2.toString(),
                        thirdPeriodScore = el.scores.home.quarter_3.toString() + "-" + el.scores.away.quarter_3.toString(),
                        fourthPeriodScore = el.scores.home.quarter_4.toString() + "-" + el.scores.away.quarter_4.toString(),
                        fifthPeriodScore = null
                    )
                )
            }
            val basketballAdapter = SportsRecycleAdapter(basketballList)
            basketballRecyclerView.adapter = basketballAdapter

//            val customSnapHelper: SnapHelper = PagerSnapHelper()
//            customSnapHelper.attachToRecyclerView(customRecyclerView)
        }
    }
}