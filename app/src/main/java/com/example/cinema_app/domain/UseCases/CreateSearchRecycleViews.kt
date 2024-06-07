package com.example.cinema_app.domain.UseCases

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.data.Api.DataClasses.Films.CustomDataClass
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SportEvent
import com.example.cinema_app.data.adapters.CustomRecycleAdapter
import com.example.cinema_app.data.adapters.SportsRecycleAdapter

class CreateSearchRecycleViews{
    fun create_recycle_view(context: Context?, recyclerView: RecyclerView, type:String, genres:ArrayList<String>, searchQuery:String)
    {
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)

        if(type == "movies") {
            var list: MutableList<CustomDataClass> = mutableListOf()
            for(it in SearchData.moviesList)
            {
                if (list.contains(it)) {
                    continue
                }
                if(genres.size == 0 && searchQuery.length == 0) {
                    list = SearchData.moviesList
                    break
                }
                if(genres.size>0)
                    for(genre in genres)
                        if(it.Genre.contains(genre) && searchQuery.length==0)
                            list.add(it)
                        else if(it.name.toLowerCase().contains(searchQuery.toLowerCase()))
                            list.add(it)

            }
            val adapter = CustomRecycleAdapter(list)
            recyclerView.adapter = adapter
        }

        if(type == "series") {
            var list: MutableList<CustomDataClass> = mutableListOf()
            for(it in SearchData.seriesList)
            {
                if (list.contains(it)) {
                    continue
                }
                if(genres.size == 0 && searchQuery.length == 0) {
                    list = SearchData.seriesList
                    break
                }
                if(genres.size>0)
                    for(genre in genres)
                        if(it.Genre.contains(genre) && searchQuery.length==0)
                            list.add(it)
                        else if(it.name.toLowerCase().contains(searchQuery.toLowerCase()))
                            list.add(it)
            }
            val adapter = CustomRecycleAdapter(list)
            recyclerView.adapter = adapter
        }

        if(type == "sports") {
            recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            var list: MutableList<SportEvent> = mutableListOf()

            if(genres.size == 0)
            {
                list.addAll(SearchData.footballList)
                list.addAll(SearchData.basketballList)
                list.addAll(SearchData.volleyballList)
                list.addAll(SearchData.hockeyList)
            }

            if(genres.size == 0 && searchQuery.length > 0)
            {
                for(it in SearchData.footballList)
                    if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                        list.add(it)

                for(it in SearchData.basketballList)
                    if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                        list.add(it)

                for(it in SearchData.volleyballList)
                    if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                        list.add(it)

                for(it in SearchData.hockeyList)
                    if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                        list.add(it)
            }

            if(genres.contains("футбол") && searchQuery == "")
                list.addAll(SearchData.footballList)
            else if(genres.contains("футбол") && searchQuery.length>0)
                for(it in SearchData.footballList)
                        if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                            list.add(it)

            if(genres.contains("баскетбол") && searchQuery == "")
                list.addAll(SearchData.basketballList)
            else if(genres.contains("баскетбол") && searchQuery.length>0)
                for(it in SearchData.basketballList)
                    if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                        list.add(it)

            if(genres.contains("воллейбол") && searchQuery == "")
                list.addAll(SearchData.volleyballList)
            else if(genres.contains("воллейбол") && searchQuery.length>0)
                for(it in SearchData.volleyballList)
                    if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                        list.add(it)

            if(genres.contains("хоккей") && searchQuery == "")
                list.addAll(SearchData.hockeyList)
            else if(genres.contains("хоккей") && searchQuery.length>0)
                for(it in SearchData.hockeyList)
                    if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                        list.add(it)

            val adapter = SportsRecycleAdapter(list)
            recyclerView.adapter = adapter
        }
    }

    fun create_recycle_views(context: Context?, filmsView: RecyclerView, seriesView: RecyclerView, sportsView: RecyclerView,genres:ArrayList<String>, searchQuery:String) {
        var filmsList: MutableList<CustomDataClass> = mutableListOf()
        var seriessList: MutableList<CustomDataClass> = mutableListOf()
        var sportsList: MutableList<SportEvent> = mutableListOf()

        filmsView.setHasFixedSize(false)
        filmsView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)

        seriesView.setHasFixedSize(false)
        seriesView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)

        sportsView.setHasFixedSize(false)
        sportsView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        if(genres.size == 0 && searchQuery == "")
        {
            filmsList.addAll(SearchData.moviesList)
            seriessList.addAll(SearchData.seriesList)
            sportsList.addAll(SearchData.footballList)
            sportsList.addAll(SearchData.basketballList)
            sportsList.addAll(SearchData.volleyballList)
            sportsList.addAll(SearchData.hockeyList)
        }

        if(genres.size == 0 && searchQuery.length>0)
        {
            for(it in SearchData.moviesList)
                if (it.name.toLowerCase().contains(searchQuery.toLowerCase()))
                    filmsList.add(it)
            for(it in SearchData.seriesList)
                if (it.name.toLowerCase().contains(searchQuery.toLowerCase()))
                    seriessList.add(it)
            for(it in SearchData.footballList)
                if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                    sportsList.add(it)

            for(it in SearchData.basketballList)
                if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                    sportsList.add(it)

            for(it in SearchData.volleyballList)
                if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                    sportsList.add(it)

            for(it in SearchData.hockeyList)
                if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                    sportsList.add(it)
        }

        for(it in SearchData.moviesList)
        {
            for(genre in genres) {
                if (it.Genre.contains(genre)) {
                    if (it.Genre.contains(genre) && searchQuery.length == 0)
                        filmsList.add(it)
                    else if (it.name.toLowerCase().contains(searchQuery.toLowerCase()))
                        filmsList.add(it)
                }
            }
        }
        val filmsAdapter = CustomRecycleAdapter(filmsList)
        filmsView.adapter = filmsAdapter

        for(it in SearchData.seriesList)
        {
            for(genre in genres) {
                if (it.Genre.contains(genre)) {
                    if (it.Genre.contains(genre) && searchQuery.length == 0)
                        seriessList.add(it)
                    else if (it.name.toLowerCase().contains(searchQuery.toLowerCase()))
                        seriessList.add(it)
                }
            }
        }
        val seriesAdapter = CustomRecycleAdapter(seriessList)
        seriesView.adapter = seriesAdapter

        if(genres.contains("футбол") && searchQuery == "")
            sportsList.addAll(SearchData.footballList)
        else if(genres.contains("футбол") && searchQuery.length>0)
            for(it in SearchData.footballList)
                if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                    sportsList.add(it)

        if(genres.contains("баскетбол") && searchQuery == "")
            sportsList.addAll(SearchData.basketballList)
        else if(genres.contains("баскетбол") && searchQuery.length>0)
            for(it in SearchData.basketballList)
                if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                    sportsList.add(it)

        if(genres.contains("воллейбол") && searchQuery == "")
            sportsList.addAll(SearchData.volleyballList)
        else if(genres.contains("воллейбол") && searchQuery.length>0)
            for(it in SearchData.volleyballList)
                if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                    sportsList.add(it)

        if(genres.contains("хоккей") && searchQuery == "")
            sportsList.addAll(SearchData.hockeyList)
        else if(genres.contains("хоккей") && searchQuery.length>0)
            for(it in SearchData.hockeyList)
                if(it.team1Name.toLowerCase().contains(searchQuery.toLowerCase()) || it.team2Name.toLowerCase().contains(searchQuery.toLowerCase()))
                    sportsList.add(it)

        val adapter = SportsRecycleAdapter(sportsList)
        sportsView.adapter = adapter
    }
}