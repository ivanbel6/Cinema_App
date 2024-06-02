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
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SlideSportItem
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SportEvent
import com.example.cinema_app.data.adapters.SliderAdapter
import com.example.cinema_app.data.adapters.SportsSliderAdapter
import com.example.cinema_app.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


lateinit var scrollRunnable1: Runnable

class CreateSportsTopSlider(val linearLayout: LinearLayout, val genreRecyclerView: RecyclerView) {
    companion object {
        var firstVisibleItemPosition: Int = 0
    }

    fun fillTopSportsSlider(
        applicationContext: Context,
        sliderRecyclerView: RecyclerView,
        scrollHandler: Handler,
    )

    {
        sliderRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper1: SnapHelper = PagerSnapHelper()
        snapHelper1.attachToRecyclerView(sliderRecyclerView)

        val slideItems = mutableListOf<SlideSportItem>()

        val apiInterface = MainActivity.basketballretrofit.create(ApiInterface::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val sportsList = apiInterface.getBasketballResponse()

            for (el in sportsList.response) {

                val instant = Instant.ofEpochSecond(el.timestamp)
                val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Define your desired date format here
                val formattedDate = date.format(formatter)

                slideItems.add(
                    SlideSportItem(
                        nameSportEvent = "Матч регулярного чемпионата NBA",
                        location = el.country.name,
                        date = formattedDate
                    )
                )
                if (slideItems.size > 4) {
                    break;
                }
            }
            Log.v("Test", slideItems.toString())

            val sliderAdapter = SportsSliderAdapter(slideItems, applicationContext)
            sliderRecyclerView.adapter = sliderAdapter

            sliderRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    CreateTopSlider.firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    // Обновляем индикатор при пролистывании
                    for (i in 0 until linearLayout.size) {
                        if (i == CreateTopSlider.firstVisibleItemPosition) {
                            linearLayout[i].setBackgroundResource(R.drawable.active_indicator)
                        } else {
                            // Устанавливаем фоновый ресурс неактивного состояния
                            linearLayout[i].setBackgroundResource(R.drawable.inactive_indicator)
                        }
                    }

                }
            })

            scrollRunnable1 = Runnable {
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

