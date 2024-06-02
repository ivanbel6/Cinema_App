package com.example.cinema_app.domain.UseCases

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.cinema_app.data.Api.DataClasses.CyberSportEvent.ProcessedCyberSportEvent
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.adapters.CyberSportRecycleAdapter
//import com.example.cinema_app.data.adapters.CyberSportRecycleAdapter
import com.example.cinema_app.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class CreateCyberSportSlider {
    fun fill(cyberSportRecyclerView: RecyclerView, applicationContext: Context) {
        cyberSportRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val sportsApiInterface = MainActivity.cybersportretrofit.create(ApiInterface::class.java)
        val cyberSportlList: MutableList<ProcessedCyberSportEvent> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val responseList = sportsApiInterface.getCyberSportResponse()
            for (i in 1..5) {

                val team1LogoUrl = GlideUrl(
                    "https://esportapi1.p.rapidapi.com/api/esport/team/" + responseList.events[i].homeTeam.id.toString() + "/image", LazyHeaders.Builder()
                        .addHeader("X-RapidAPI-Key", "943c0a2ab6msh980c19e8a55d692p15dba6jsnbefbbca02c4c")
                        .build()
                )

                val team2LogoUrl = GlideUrl(
                    "https://esportapi1.p.rapidapi.com/api/esport/team/" + responseList.events[i].awayTeam.id.toString() + "/image", LazyHeaders.Builder()
                        .addHeader("X-RapidAPI-Key", "943c0a2ab6msh980c19e8a55d692p15dba6jsnbefbbca02c4c")
                        .build()
                )

                val instant = Instant.ofEpochSecond(responseList.events[i].startTimestamp)
                val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                val time_formatter = DateTimeFormatter.ofPattern("HH:mm:ss") // Define your desired date format here
                val date_formatter = DateTimeFormatter.ofPattern("dd MMM yyyy") // Define your desired date format here
                val time = dateTime.format(time_formatter)
                val date = dateTime.format(date_formatter)

                cyberSportlList.add(
                    ProcessedCyberSportEvent(
                        disciplineName = responseList.events[i].tournament.category.name,
                        team1Name = responseList.events[i].homeTeam.name,
                        team2Name = responseList.events[i].awayTeam.name,
                        time = time,
                        date = date,
                        team1LogoUrl = team1LogoUrl,
                        team2LogoUrl = team2LogoUrl
                    )
                )
            }
            val cyberSportAdapter = CyberSportRecycleAdapter(cyberSportlList)
            cyberSportRecyclerView.adapter = cyberSportAdapter

//            val customSnapHelper: SnapHelper = PagerSnapHelper()
//            customSnapHelper.attachToRecyclerView(customRecyclerView)
        }
    }
}
