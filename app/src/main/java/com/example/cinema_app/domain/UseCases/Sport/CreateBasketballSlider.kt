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
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class CreateBasketballSlider {
    fun fill(basketballRecyclerView: RecyclerView, applicationContext: Context) {
        basketballRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val sportsApiInterface = MainActivity.basketballretrofit.create(ApiInterface::class.java)
        val basketballList: MutableList<SportEvent> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val responseList = sportsApiInterface.getBasketballResponse()
            for (el in responseList.response) {

                val instant = Instant.ofEpochSecond(el.timestamp)
                val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Define your desired date format here
                val formattedDate = date.format(formatter)

                basketballList.add(
                    SportEvent(
                        sportEventName = "Матч регулярного чемпионата NBA",
                        team1Name = el.teams.home.name,
                        team1Country = "USA",
                        team2Name = el.teams.away.name,
                        team2Country = "USA",
                        dateTime = formattedDate.toString(),
                        team1LogoUrl = el.teams.home.logo,
                        team2LogoUrl = el.teams.away.logo
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