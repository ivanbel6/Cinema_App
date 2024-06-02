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

                if(el.teams.home.name == "Lada" || el.teams.away.name == "Lada" || el.teams.home.name == "Dynamo Moscow" || el.teams.home.name == "Dynamo Moscow"|| el.teams.home.name == "Vityaz Balashikha" || el.teams.away.name == "Vityaz Balashikha")
                    continue
                else
                    hockeyList.add(
                        SportEvent(
                            sportEventName = "Матч регулярного чемпионата КХЛ",
                            team1Name = el.teams.home.name,
                            team1Country = "Russia",
                            team2Name = el.teams.away.name,
                            team2Country = "Russia",
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