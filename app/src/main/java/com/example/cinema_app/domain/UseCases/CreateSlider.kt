package com.example.cinema_app.domain.UseCases

import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cinema_app.data.Api.DataClasses.Films.CustomDataClass
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.adapters.CustomRecycleAdapter
import com.example.cinema_app.presentation.MainActivity
import com.example.cinema_app.presentation.MoviesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateSlider(private val viewModel: MoviesViewModel) {
    /**
     * function description
     * @param customRecyclerView
     * @param applicationContext
     * @param genre
     */

    fun fillFilms(customRecyclerView: RecyclerView, applicationContext: Context, genre: String) {
        customRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        viewModel.movies.observe(customRecyclerView.context as MainActivity, Observer { movieList ->
            val customList: MutableList<CustomDataClass> = mutableListOf()
            for (i in movieList.docs) {
                if (i.genres.toString().contains(genre) && i.description.isNotEmpty()) {
                    customList.add(
                        CustomDataClass(
                            bgImage = i.poster,
                            name = i.name,
                            time = "${i.movieLength} мин",
                            date = i.year,
                            persons = i.persons,
                            Genre = i.genres.toString().replace(Regex("[name|Genre|\\[|\\]|\\(|\\)|=]"), ""),
                            backdrop = i.backdrop,
                            videos = i.videos,
                            description = i.description,
                            countries = i.countries,
                            ageRating = i.ageRating,
                            premiere = i.premiere
                        )
                    )
                }
            }

            val customAdapter = CustomRecycleAdapter(customList)
            customRecyclerView.adapter = customAdapter
        })
    }
    fun fillSeries(customRecyclerView: RecyclerView, applicationContext: Context, genre: String) {///вапвап
        customRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        val apiInterface = MainActivity.retrofit.create(ApiInterface::class.java)
        val customList: MutableList<CustomDataClass> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val seriesList = apiInterface.getSeries()
            for (i in seriesList.docs) {
                if (i.genres.toString()
                        .contains(genre) && i.description !=""){
                    customList.add(
                        CustomDataClass(
                            bgImage = i.poster,
                            name = i.name,
                            time = i.seriesLength.toString()+" мин",
                            date = i.year,
                            persons = i.persons,
                            Genre = i.genres.toString()
                                .replace(Regex("Genre|\\[|\\|\\(|\\)|=]"), ""),
                            backdrop = i.backdrop,
                            videos = i.videos,
                            description = i.description,
                            countries = i.countries,
                            // releaseYears = i.releaseYears,
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