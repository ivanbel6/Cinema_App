package com.example.cinema_app.domain.UseCases

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cinema_app.data.Api.DataClasses.CustomDataClass
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.CustomRecycleAdapter
import com.example.cinema_app.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateSlider {
    /**
     * function description
     * @param customRecyclerView
     * @param applicationContext
     * @param genre
     */

    fun fill(customRecyclerView: RecyclerView, applicationContext: Context, genre: String) {///вапвап
        customRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        val apiInterface = MainActivity.retrofit.create(ApiInterface::class.java)
        val customList: MutableList<CustomDataClass> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val movieList = apiInterface.getMovies()
            for (i in movieList.docs) {
                if (i.genres.toString()
                        .contains(genre) && i.description !=""){
                    customList.add(
                        CustomDataClass(
                            bgImage = i.poster,
                            name = i.name,
                            time = i.movieLength.toString()+"mins",
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

            }

            val customAdapter = CustomRecycleAdapter(customList)
            customRecyclerView.adapter = customAdapter

            val customSnapHelper: SnapHelper = PagerSnapHelper()
            customSnapHelper.attachToRecyclerView(customRecyclerView)
        }
    }
}