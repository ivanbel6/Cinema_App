package com.example.cinema_app.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cinema_app.R
import com.example.cinema_app.data.DB.MainDb
import com.example.cinema_app.data.VpAdapter
import com.example.cinema_app.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val fragList = listOf(
        HomeFragment.newInstance(),
        SportFragment.newInstance(),
        MovieFragment.newInstance(),
        TVFragment.newInstance()
    )
    private val fragListTitles = listOf(
        "Home",
        "Sport",
        "Movie",
        "Tv Series",
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = VpAdapter(this, fragList)
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tb, binding.vp2) { tab, pos ->
            tab.text = fragListTitles[pos]
        }.attach()
        
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
                    fragmentTransaction.replace(R.id.fragment_container, MovieFragment())
                    fragmentTransaction.commit()
                    true
                }

                R.id.BottomNavSearch -> {
                    //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SearchFragment()).commit()
                    true
                }

                R.id.BottomNavProfile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }


    }

    companion object {
        const val AUTO_SCROLL_DELAY = 10000L // Задержка в миллисекундах (4 секунды)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}






