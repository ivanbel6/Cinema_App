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
private lateinit var dramaRecycleView: RecyclerView
private lateinit var fightRecycleView: RecyclerView
private lateinit var comedyRecycleView: RecyclerView
private lateinit var horrorsRecycleView: RecyclerView
private lateinit var scienceFictionRecycleView: RecyclerView
private lateinit var cartoonsRecycleView: RecyclerView
private lateinit var adventureRecycleView: RecyclerView
private lateinit var animRecycleView: RecyclerView

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
        CreatePopularSlider().fillPopularSlider(popularRecycleView, applicationContext, "")

        //SLIDER ON THE TOP
        topRecycleView = findViewById(R.id.sliderRecyclerView)
        CreateTopSlider().fillTopSlider(applicationContext, topRecycleView, scrollHandler)
        //DRAMA SLIDER
        dramaRecycleView = findViewById(R.id.dramaRecyclerView)
        CreatePopularSlider().fillPopularSlider(dramaRecycleView, applicationContext, "драма")
        //FIGHT SLIDER
        fightRecycleView = findViewById(R.id.fightRecyclerView)
        CreatePopularSlider().fillPopularSlider(fightRecycleView, applicationContext, "боевик")
        //COMEDY SLIDER
        comedyRecycleView = findViewById(R.id.comedyRecyclerView)
        CreatePopularSlider().fillPopularSlider(comedyRecycleView, applicationContext, "комедия")
        //HORRORS SLIDER
        horrorsRecycleView = findViewById(R.id.horrorsRecyclerView)
        CreatePopularSlider().fillPopularSlider(horrorsRecycleView, applicationContext, "ужасы")
        //SCIENCE FICTION SLIDER
        scienceFictionRecycleView = findViewById(R.id.scienceFictionRecyclerView)
        CreatePopularSlider().fillPopularSlider(scienceFictionRecycleView, applicationContext, "фантастика")
        //CATOONS SLIDER
        cartoonsRecycleView = findViewById(R.id.cartoonsRecyclerView)
        CreatePopularSlider().fillPopularSlider(cartoonsRecycleView, applicationContext, "мультфильм")
        //ADVENTURE SLIDER
        adventureRecycleView = findViewById(R.id.adventuresRecyclerView)
        CreatePopularSlider().fillPopularSlider(adventureRecycleView, applicationContext, "приключения")
        //ANIMATION SLIDER
        animRecycleView= findViewById(R.id.animRecyclerView)
        CreatePopularSlider().fillPopularSlider(animRecycleView, applicationContext, "аниме")
    }

    companion object {
        const val AUTO_SCROLL_DELAY = 10000L // Задержка в миллисекундах (4 секунды)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}






