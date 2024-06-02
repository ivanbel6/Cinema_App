package com.example.cinema_app.domain.UseCases

import com.example.cinema_app.data.Api.DataClasses.CyberSportEvent.ProcessedCyberSportEvent
import com.example.cinema_app.data.Api.DataClasses.Films.CustomDataClass
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SportEvent
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.adapters.CyberSportRecycleAdapter
import com.example.cinema_app.data.adapters.SportsRecycleAdapter
import com.example.cinema_app.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class SearchData {

    companion object {
        val basketballList: MutableList<SportEvent> = mutableListOf()
        val cyberSportlList: MutableList<ProcessedCyberSportEvent> = mutableListOf()
        val footballList: MutableList<SportEvent> = mutableListOf()
        val hockeyList: MutableList<SportEvent> = mutableListOf()
        val volleyballList: MutableList<SportEvent> = mutableListOf()
        val seriesList: MutableList<CustomDataClass> = mutableListOf()
        val moviesList: MutableList<CustomDataClass> = mutableListOf()
    }

    fun prepare() {
        basketballList.clear()
        cyberSportlList.clear()
        footballList.clear()
        hockeyList.clear()

        CoroutineScope(Dispatchers.Main).launch {
            // все фильмы
            val moviesApiInterface = MainActivity.retrofit.create(ApiInterface::class.java)
            val moviesResponseList = moviesApiInterface.getMovies()

            for (i in moviesResponseList.docs) {
                moviesList.add(
                    CustomDataClass(
                        bgImage = i.poster,
                        name = i.name,
                        time = i.movieLength.toString() + " мин",
                        date = i.year,
                        persons = i.persons,
                        Genre = i.genres.toString()
                            .replace(Regex("[name|Genre|\\[|\\]|\\(|\\)|=]"), ""),
                        backdrop = i.backdrop,
                        videos = i.videos,
                        description = i.description,
                        countries = i.countries,
//                            releaseYears = i.releaseYears,
                        ageRating = i.ageRating,
                        premiere = i.premiere
                    )
                )
            }

            // все сериалы
            val seriesApiInterface = MainActivity.retrofit.create(ApiInterface::class.java)
            val seriesResponseList = seriesApiInterface.getSeries()

            for (i in seriesResponseList.docs) {
                seriesList.add(
                    CustomDataClass(
                        bgImage = i.poster,
                        name = i.name,
                        time = i.seriesLength.toString() + " мин",
                        date = i.year,
                        persons = i.persons,
                        Genre = i.genres.toString()
                            .replace(Regex("[name|Genre|\\[|\\]|\\(|\\)|=]"), ""),
                        backdrop = i.backdrop,
                        videos = i.videos,
                        description = i.description,
                        countries = i.countries,
//                            releaseYears = i.releaseYears,
                        ageRating = i.ageRating,
                        premiere = i.premiere
                    )
                )
            }

            // весь футбол
            val footballApiInterface = MainActivity.footballretrofit.create(ApiInterface::class.java)
            val footballResponseList = footballApiInterface.getFootballResponse()

            for (el in footballResponseList.response) {

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

            // весь кмберспорт
            val cyberSportApiInterface = MainActivity.cybersportretrofit.create(ApiInterface::class.java)
            val cyberSportResponseList = cyberSportApiInterface.getCyberSportResponse()

            for (i in 1..5) {

//                val team1LogoUrl = GlideUrl(
//                    "https://esportapi1.p.rapidapi.com/api/esport/team/" + responseList.events[i].homeTeam.id.toString() + "/image", LazyHeaders.Builder()
//                        .addHeader("X-RapidAPI-Key", "943c0a2ab6msh980c19e8a55d692p15dba6jsnbefbbca02c4c")
//                        .build()
//                )
//
//                val team2LogoUrl = GlideUrl(
//                    "https://esportapi1.p.rapidapi.com/api/esport/team/" + responseList.events[i].awayTeam.id.toString() + "/image", LazyHeaders.Builder()
//                        .addHeader("X-RapidAPI-Key", "943c0a2ab6msh980c19e8a55d692p15dba6jsnbefbbca02c4c")
//                        .build()
//                )

                val team1LogoUrl = null
                val team2LogoUrl = null

                val instant = Instant.ofEpochSecond(cyberSportResponseList.events[i].startTimestamp)
                val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                val time_formatter = DateTimeFormatter.ofPattern("HH:mm:ss") // Define your desired date format here
                val date_formatter = DateTimeFormatter.ofPattern("dd MMM yyyy") // Define your desired date format here
                val time = dateTime.format(time_formatter)
                val date = dateTime.format(date_formatter)

                cyberSportlList.add(
                    ProcessedCyberSportEvent(
                        disciplineName = cyberSportResponseList.events[i].tournament.category.name,
                        team1Name = cyberSportResponseList.events[i].homeTeam.name,
                        team2Name = cyberSportResponseList.events[i].awayTeam.name,
                        time = time,
                        date = date,
                        team1LogoUrl = team1LogoUrl,
                        team2LogoUrl = team2LogoUrl
                    )
                )
            }

            // весь баскетбол
            val basketballApiInterface = MainActivity.basketballretrofit.create(ApiInterface::class.java)
            val basketballResponseList = basketballApiInterface.getBasketballResponse()

            for (el in basketballResponseList.response) {
                val instant = Instant.ofEpochSecond(el.timestamp)
                val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                val formatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy") // Define your desired date format here
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

            // весь хоккей
            val hockeyApiInterface = MainActivity.hockeyretrofit.create(ApiInterface::class.java)
            val hockeyResponseList = hockeyApiInterface.getHockeyResponse()

            for (el in hockeyResponseList.response) {

                val instant = Instant.ofEpochSecond(el.timestamp)
                val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Define your desired date format here
                val formattedDate = date.format(formatter)

                if(el.teams.home.name == "Lada" || el.teams.away.name == "Lada" || el.teams.home.name == "Dynamo Moscow" || el.teams.home.name == "Dynamo Moscow" || el.teams.home.name == "Vityaz Balashikha" || el.teams.away.name == "Vityaz Balashikha")
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

            // весь воллейбол
            val volleyballApiInterface = MainActivity.volleyballretrofit.create(ApiInterface::class.java)
            val volleyballResponseList = volleyballApiInterface.getVolleyballResponse()

            for (el in volleyballResponseList.response) {

                val instant = Instant.ofEpochSecond(el.timestamp)
                val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Define your desired date format here
                val formattedDate = date.format(formatter)

                if(el.teams.home.name != "Orenburg" && el.teams.away.name != "Orenburg")
                {
                    volleyballList.add(
                        SportEvent(
                            sportEventName = "Матч регулярного чемпионата по воллейболу среди мужчин",
                            team1Name = el.teams.home.name,
                            team1Country = "Россия",
                            team2Name = el.teams.away.name,
                            team2Country = "Россия",
                            dateTime = formattedDate.toString(),
                            team1LogoUrl = el.teams.home.logo,
                            team2LogoUrl = el.teams.away.logo
                        )
                    )
                }
            }
        }


    }
}