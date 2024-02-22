package com.example.cinema_app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.CustomDataClass
import com.example.cinema_app.data.Api.DataClasses.Poster
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.SlideItem
import com.example.cinema_app.data.SliderAdapter
import com.example.cinema_app.domain.CreateAndFillCustomRecycleView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private lateinit var customRecyclerView: RecyclerView

private val scrollHandler = Handler()
private lateinit var scrollRunnable: Runnable
private lateinit var sliderRecyclerView: RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.isItemActiveIndicatorEnabled = false
        bottomNavigation.itemPaddingBottom = 30


        customRecyclerView = findViewById(R.id.CustomRecycleView)
        val doIt = CreateAndFillCustomRecycleView()
        doIt.fill(customRecyclerView, applicationContext)


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //FIRST SLIDER ON THE TOP
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        sliderRecyclerView = findViewById(R.id.sliderRecyclerView)
        sliderRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper1: SnapHelper = PagerSnapHelper()
        snapHelper1.attachToRecyclerView(sliderRecyclerView)
        val slideItems = mutableListOf<SlideItem>()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val movieList = apiInterface.getMovieNew()
            Log.v("CasualRecycleView", "movie description: $movieList")
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
                scrollHandler.postDelayed(scrollRunnable, AUTO_SCROLL_DELAY)
            }

            scrollHandler.postDelayed(scrollRunnable, AUTO_SCROLL_DELAY)
        }





    }

    companion object {
        private const val AUTO_SCROLL_DELAY = 10000L // Задержка в миллисекундах (4 секунды)

    }
}




