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


class CreateFootballSlider {
    fun fill(footballRecyclerView: RecyclerView, applicationContext: Context) {
        footballRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val sportsApiInterface = MainActivity.footballretrofit.create(ApiInterface::class.java)
        val footballList: MutableList<SportEvent> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val footballResponseList = sportsApiInterface.getFootballResponse()

            for (el in footballResponseList.response) {
                var extraTimeScore:String? = null
                var penaltyScore:String? = null

                val instant = Instant.ofEpochSecond(el.fixture.timestamp)
                val dateTimeInstant = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm") // Define your desired date format here
                val dateTime = dateTimeInstant.format(formatter)


                if(el.score.extratime.home != null)
                    extraTimeScore = el.score.extratime.home.toString() + "-" + el.score.extratime.away.toString()
                if(el.score.penalty.home != null)
                    penaltyScore = el.score.penalty.home.toString() + "-" + el.score.penalty.away.toString()

               footballList.add(
                    SportEvent(
                        type = "футбол",
                        sportEventName = "Матч регулярного чемпионата Российская Премьер-Лига",
                        imageUrl = "https://www.luzhniki.ru/media/images/a92363.2e16d0ba.fill-1240x500.format-jpeg.jpg",
                        matchStatus = el.fixture.status.long,
                        leagueLogoUrl = el.league.logo,
                        leagueFlagUrl = el.league.flag,
                        leagueName = el.league.name,
                        leagueCountry = el.league.country,
                        leagueSeason = el.league.season.toString(),
                        leagueType = "Cup",
                        team1Name = el.teams.home.name,
                        team1Country = el.league.country,
                        team2Name = el.teams.away.name,
                        team2Country = el.league.country,
                        dateTime = dateTime,
                        team1LogoUrl = el.teams.home.logo,
                        team1Score = el.score.fulltime.home.toString(),
                        team2LogoUrl = el.teams.away.logo,
                        team2Score = el.score.fulltime.away.toString(),
                        firstTimeScore = el.score.halftime.home.toString() + "-" + el.score.halftime.away.toString(),
                        fullTimeScore = el.score.fulltime.home.toString() + "-" + el.score.fulltime.away.toString(),
                        extraTimeScore = extraTimeScore,
                        penaltyScore = penaltyScore,
                        firstPeriodScore = null,
                        secondPeriodScore = null,
                        thirdPeriodScore = null,
                        fourthPeriodScore = null,
                        fifthPeriodScore = null
                    )
                )
                Log.d("object create",footballList.toString())
            }
            Log.d("all list", footballList.toString())
            val footballAdapter = SportsRecycleAdapter(footballList)
            footballRecyclerView.adapter = footballAdapter

//            val customSnapHelper: SnapHelper = PagerSnapHelper()
//            customSnapHelper.attachToRecyclerView(customRecyclerView)
        }
    }
}