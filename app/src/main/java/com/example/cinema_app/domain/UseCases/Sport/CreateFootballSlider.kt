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


class CreateFootballSlider {
    fun fill(footballRecyclerView: RecyclerView, applicationContext: Context) {
        footballRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val sportsApiInterface = MainActivity.footballretrofit.create(ApiInterface::class.java)
        val footballList: MutableList<SportEvent> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val responseList = sportsApiInterface.getFootballResponse()
            for (el in responseList.response) {

                val instant = Instant.ofEpochSecond(el.fixture.timestamp)
                val dateTimeInstant = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm") // Define your desired date format here
                val dateTime = dateTimeInstant.format(formatter)

                footballList.add(
                    SportEvent(
                        sportEventName = "Матч регулярного чемпионата Российская Премьер-Лига",
                        team1Name = el.teams.home.name,
                        team1Country = "Russia",
                        team2Name = el.teams.away.name,
                        team2Country = "Russia",
                        dateTime = dateTime,
                        team1LogoUrl = el.teams.home.logo,
                        team2LogoUrl = el.teams.away.logo
                    )
                )
            }
            val footballAdapter = SportsRecycleAdapter(footballList)
            footballRecyclerView.adapter = footballAdapter

//            val customSnapHelper: SnapHelper = PagerSnapHelper()
//            customSnapHelper.attachToRecyclerView(customRecyclerView)
        }
    }
}