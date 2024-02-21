package com.example.cinema_app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.Poster
import com.example.cinema_app.data.SlideItem
import com.example.cinema_app.data.SliderAdapter
import com.example.cinema_app.domain.CreateAndFillCustomRecycleView
import com.google.android.material.bottomnavigation.BottomNavigationView


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
        sliderRecyclerView = findViewById(R.id.sliderRecyclerView)
        sliderRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper1: SnapHelper = PagerSnapHelper()
        snapHelper1.attachToRecyclerView(sliderRecyclerView)
        val poster = Poster(
            url = "https://www.wallpaperflare.com/static/954/117/151/worms-eye-view-eiffel-tower-wallpaper.jpg",
            previewUrl = "https://www.wallpaperflare.com/static/954/117/151/worms-eye-view-eiffel-tower-wallpaper.jpg")
        val slideItems = listOf(
            SlideItem(
                date = 2021,
                genres = mutableListOf("1", "2"),
                imdbRating = 8.1,
                mainImage = poster,
                tomatoesRating = 8.3
            ),
            SlideItem(
                date = 2021,
                genres = mutableListOf("1", "2"),
                imdbRating = 8.1,
                mainImage = poster,
                tomatoesRating = 8.3
            ),
        )


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

    companion object {
        private const val AUTO_SCROLL_DELAY = 10000L // Задержка в миллисекундах (4 секунды)

        @JvmStatic
        fun newInstance() = MainActivity()


    }
}




