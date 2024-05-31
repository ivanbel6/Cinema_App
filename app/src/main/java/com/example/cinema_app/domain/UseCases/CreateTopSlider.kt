package com.example.cinema_app.domain.UseCases

import android.content.Context
import android.os.Handler
import android.util.Log
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.adapters.GenreAdapter
import com.example.cinema_app.data.Api.DataClasses.Films.SlideItem
import com.example.cinema_app.data.adapters.SliderAdapter
import com.example.cinema_app.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


lateinit var scrollRunnable: Runnable

class CreateTopSlider(val linearLayout: LinearLayout, val genreRecyclerView: RecyclerView) {
    companion object {
        var firstVisibleItemPosition: Int = 0
    }

    fun fillTopSlider(
        applicationContext: Context,
        sliderRecyclerView: RecyclerView,
        scrollHandler: Handler,
    ) {
        sliderRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper1: SnapHelper = PagerSnapHelper()
        snapHelper1.attachToRecyclerView(sliderRecyclerView)
        val slideItems = mutableListOf<SlideItem>()
        val apiInterface = MainActivity.retrofit.create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            val movieList = apiInterface.getMovieNew()
            for (i in movieList.docs) {
                slideItems.add(
                    SlideItem(
                        date = i.year,
                        imdbRating = i.rating!!.imdb!!.toDouble(),
                        tomatoesRating = 7.3,
                        mainImage = i.poster,
                        genres = i.genres.toString()
                            .replace(Regex("[name|Genre|\\[|\\]|\\(|\\)|=]"), "")

                    )
                )
                if (slideItems.size > 4) {
                    break;
                }
            }
            Log.v("Test", slideItems.toString())
            CreateGenreSlider(slideItems[0].genres,applicationContext)
            val sliderAdapter = SliderAdapter(slideItems, applicationContext)
            sliderRecyclerView.adapter = sliderAdapter
            sliderRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    // Обновляем индикатор при пролистывании
                    for (i in 0 until linearLayout.size) {
                        if (i == firstVisibleItemPosition) {
                            linearLayout[i].setBackgroundResource(R.drawable.active_indicator)
                            CreateGenreSlider(slideItems[i].genres,applicationContext)
//                          genreTextView.setText(slideItems[i].genres)
                        } else {
                            // Устанавливаем фоновый ресурс неактивного состояния
                            linearLayout[i].setBackgroundResource(R.drawable.inactive_indicator)
                        }
                    }

                }
            })
            scrollRunnable = Runnable {

                val layoutManager = sliderRecyclerView.layoutManager as LinearLayoutManager
                val currentPosition = layoutManager.findFirstVisibleItemPosition()
                val nextPosition =
                    if (currentPosition < sliderAdapter.itemCount - 1) currentPosition + 1 else 0
                sliderRecyclerView.smoothScrollToPosition(nextPosition)
                scrollHandler.postDelayed(scrollRunnable, MainActivity.AUTO_SCROLL_DELAY)
            }

            scrollHandler.postDelayed(scrollRunnable, MainActivity.AUTO_SCROLL_DELAY)
        }

    }

    fun CreateGenreSlider(slideItems: String, context: Context) {
        // Разделение строки по запятым и добавление каждого элемента в список
        val genresList: List<String> = slideItems.split(", ")

        genreRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        genreRecyclerView.adapter = GenreAdapter(genresList)
    }
}

