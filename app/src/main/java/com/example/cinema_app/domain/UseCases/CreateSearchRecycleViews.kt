package com.example.cinema_app.domain.UseCases

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cinema_app.data.Api.DataClasses.Films.CustomDataClass
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SportEvent
import com.example.cinema_app.data.adapters.CustomRecycleAdapter
import com.example.cinema_app.data.adapters.SportsRecycleAdapter

class CreateSearchRecycleViews {
    fun create_recycle_view(recyclerView: RecyclerView, type:String, genres:ArrayList<String>, searchQuery:String)
    {
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        if(type == "movies") {
            var list: MutableList<CustomDataClass> = mutableListOf()
            for(it in SearchData.moviesList)
            {
                Log.d("test", it.Genre)
                if(genres.size == 0 && searchQuery.length == 0) {
                    list = SearchData.moviesList
                    break
                }
                if(genres.size>0 && it.Genre in genres)
                    list.add(it)
            }
            val adapter = CustomRecycleAdapter(list)
            recyclerView.adapter = adapter
        }

        if(type == "series") {
            var list: MutableList<CustomDataClass> = mutableListOf()
            for(it in SearchData.seriesList)
            {
                if(genres.size == 0 && searchQuery.length == 0) {
                    list = SearchData.moviesList
                    break
                }
                if(genres.size>0 && it.Genre !in genres)
                    continue;
                if(searchQuery !in it.name)
                    continue;
                list.add(it);
            }
            val adapter = CustomRecycleAdapter(list)
            recyclerView.adapter = adapter
        }

        if(type == "sports") {
            var list: MutableList<SportEvent> = mutableListOf()
            if("футбол" in genres)
                list.addAll(SearchData.footballList)
            if("баскетбол" in genres)
                list.addAll(SearchData.basketballList)
            if("воллейбол" in genres)
                list.addAll(SearchData.volleyballList)
            if("хоккей" in genres)
                list.addAll(SearchData.hockeyList)

            val adapter = SportsRecycleAdapter(list)
            recyclerView.adapter = adapter
        }
    }
}