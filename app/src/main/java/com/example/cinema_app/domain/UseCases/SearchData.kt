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
            }

//            // весь кмберспорт
//            val cyberSportApiInterface = MainActivity.cybersportretrofit.create(ApiInterface::class.java)
//            val cyberSportResponseList = cyberSportApiInterface.getCyberSportResponse()
//
//            for (i in 1..5) {

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

//                val team1LogoUrl = null
//                val team2LogoUrl = null
//
//                val instant = Instant.ofEpochSecond(cyberSportResponseList.events[i].startTimestamp)
//                val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
//                val time_formatter = DateTimeFormatter.ofPattern("HH:mm:ss") // Define your desired date format here
//                val date_formatter = DateTimeFormatter.ofPattern("dd MMM yyyy") // Define your desired date format here
//                val time = dateTime.format(time_formatter)
//                val date = dateTime.format(date_formatter)
//
//                cyberSportlList.add(
//                    ProcessedCyberSportEvent(
//                        disciplineName = cyberSportResponseList.events[i].tournament.category.name,
//                        team1Name = cyberSportResponseList.events[i].homeTeam.name,
//                        team2Name = cyberSportResponseList.events[i].awayTeam.name,
//                        time = time,
//                        date = date,
//                        team1LogoUrl = team1LogoUrl,
//                        team2LogoUrl = team2LogoUrl
//                    )
//                )
//            }

            // весь баскетбол
            val basketballApiInterface = MainActivity.basketballretrofit.create(ApiInterface::class.java)
            val basketballResponseList = basketballApiInterface.getBasketballResponse()

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

            // весь хоккей
            val hockeyApiInterface = MainActivity.hockeyretrofit.create(ApiInterface::class.java)
            val hockeyResponseList = hockeyApiInterface.getHockeyResponse()

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

            // весь воллейбол
            val volleyballApiInterface = MainActivity.volleyballretrofit.create(ApiInterface::class.java)
            val volleyballResponseList = volleyballApiInterface.getVolleyballResponse()

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
                            type = "воллейбол",
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
        }
    }
}