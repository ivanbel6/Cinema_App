package com.example.cinema_app.domain.UseCases

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.SlideItem
import com.example.cinema_app.data.SliderAdapter
import com.example.cinema_app.presentation.MainActivity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
lateinit var scrollRunnable: Runnable
class CreateTopSlider {
    fun fillTopSlider(
        applicationContext: Context,
        sliderRecyclerView: RecyclerView,
        scrollHandler: Handler
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
                if (i.names.isNotEmpty() && i.description != null && i.genres.isNotEmpty() && i.poster.previewUrl != null && i.rating?.kp != null) {
                    slideItems.add(
                        SlideItem(
                            date = 2021,
                            imdbRating = 8.3,
                            tomatoesRating = 8.1,
                            mainImage = i.poster,
                            genres = i.genres.toString()
                                .replace(Regex("[name|Genre|\\[|\\]|\\(|\\)|=]"), "")

                        )
                    )

                } else {
                    Log.e("Error", "Some fields are null for movie ${i.names}")
                }
            }
            val sliderAdapter = SliderAdapter(slideItems, applicationContext)
            sliderRecyclerView.adapter = sliderAdapter
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
}