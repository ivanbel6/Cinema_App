package com.example.cinema_app.domain.UseCases.Sport

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.data.Api.DataClasses.Films.SportEvent
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.adapters.SportsRecycleAdapter
import com.example.cinema_app.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class CreateHockeySlider {
    fun fill(hockeyRecyclerView: RecyclerView, applicationContext: Context) {
        hockeyRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val sportsApiInterface = MainActivity.hockeyretrofit.create(ApiInterface::class.java)
        val hockeyList: MutableList<SportEvent> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val responseList = sportsApiInterface.getHockeyResponse()
            for (el in responseList.response) {

                val instant = Instant.ofEpochSecond(el.timestamp)
                val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Define your desired date format here
                val formattedDate = date.format(formatter)

                hockeyList.add(
                    SportEvent(
                        team1Name = el.teams.home.name,
                        team2Name = el.teams.away.name,
                        dateTime = formattedDate.toString(),
                        team1LogoUrl = el.teams.home.logo,
                        team2LogoUrl = el.teams.away.logo
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