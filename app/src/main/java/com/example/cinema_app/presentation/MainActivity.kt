package com.example.cinema_app.presentation

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.domain.UseCases.CreatePopularSlider
import com.example.cinema_app.domain.UseCases.CreateTopSlider
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private lateinit var popularRecycleView: RecyclerView
private lateinit var topRecycleView: RecyclerView
private val scrollHandler = Handler()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Нижняя навигация
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.isItemActiveIndicatorEnabled = false
        bottomNavigation.itemPaddingBottom = 30

        //Slider popular
        popularRecycleView = findViewById(R.id.CustomRecycleView)
        val doIt = CreatePopularSlider()
        doIt.fillPopularSlider(popularRecycleView, applicationContext)

        //SLIDER ON THE TOP
        topRecycleView = findViewById(R.id.sliderRecyclerView)
        CreateTopSlider().fillTopSlider(applicationContext, topRecycleView,scrollHandler)
    }

    companion object {
        const val AUTO_SCROLL_DELAY = 10000L // Задержка в миллисекундах (4 секунды)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}






