package com.example.cinema_app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.domain.UseCases.CreateSlider
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
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val background_image = findViewById<ImageView>(R.id.background_image)
        background_image.scaleType = ImageView.ScaleType.CENTER_CROP

        /**
         * BOTTOM NAVIGATION
         */
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.isItemActiveIndicatorEnabled = false
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.BottomNavHome -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_container, HomeFragment())
                    fragmentTransaction.commit()
                    true
                }
                R.id.BottomNavSearch -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SearchFragment()).commit()
                    true
                }
                R.id.BottomNavProfile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }

        /**
         * SLIDER ON THE TOP
         */
        topRecycleView = findViewById(R.id.sliderRecyclerView)
        CreateTopSlider(this).fillTopSlider(applicationContext, topRecycleView, scrollHandler)


        /**
         * SLIDER POPULAR
//         */
        popularRecycleView = findViewById(R.id.CustomRecycleView)
        CreateSlider().fill(popularRecycleView, applicationContext, "")
        /**
         * DRAMA SLIDER
         */
        dramaRecycleView = findViewById(R.id.dramaRecyclerView)
        CreateSlider().fill(dramaRecycleView, applicationContext, "драма")
        /**
//         * FIGHT SLIDER
//         */
        fightRecycleView = findViewById(R.id.fightRecyclerView)
        CreateSlider().fill(fightRecycleView, applicationContext, "боевик")
        /**
         * COMEDY SLIDER
         */
        comedyRecycleView = findViewById(R.id.comedyRecyclerView)
        CreateSlider().fill(comedyRecycleView, applicationContext, "комедия")
        /**
         * HORRORS SLIDER
         */
        horrorsRecycleView = findViewById(R.id.horrorsRecyclerView)
        CreateSlider().fill(horrorsRecycleView, applicationContext, "ужасы")
        /**
         * FANTASTIC  SLIDER
         */
        scienceFictionRecycleView = findViewById(R.id.scienceFictionRecyclerView)
        CreateSlider().fill(scienceFictionRecycleView, applicationContext, "фантастика")
        /**
         * CATOONS SLIDER
         */
        cartoonsRecycleView = findViewById(R.id.cartoonsRecyclerView)
        CreateSlider().fill(cartoonsRecycleView, applicationContext, "мультфильм")
        /**
         * ADVENTURE SLIDER
         */
        adventureRecycleView = findViewById(R.id.adventuresRecyclerView)
        CreateSlider().fill(adventureRecycleView, applicationContext, "приключения")
        /**
         * ANIMATION SLIDER
         */
        animRecycleView= findViewById(R.id.animRecyclerView)
        CreateSlider().fill(animRecycleView, applicationContext, "аниме")
    }

    companion object {
        const val AUTO_SCROLL_DELAY = 10000L // Задержка в миллисекундах (4 секунды)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}






