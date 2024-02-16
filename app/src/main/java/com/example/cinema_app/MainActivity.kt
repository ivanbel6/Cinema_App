package com.example.cinema_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cinema_app.Api.Interface.ApiInterface
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var recyclerView: RecyclerView
private lateinit var listItem: List<User>

private lateinit var customRecyclerView: RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.isItemActiveIndicatorEnabled = false
        bottomNavigation.itemPaddingBottom = 30
        listItem = listOf(
            User(name = "Ivan", surname = "Bel")
        )

        // Use this to programmatically apply behavior attributes; eg.
        // standardBottomSheetBehavior.setState(STATE_EXPANDED);
        customRecyclerView = findViewById(R.id.CustomRecycleView)
        customRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)
        val customList: MutableList<CustomDataClass> = mutableListOf()
        CoroutineScope(Dispatchers.Main).launch {
            val movieList = apiInterface.getMovies()
            movieList.docs[0].description
            Log.v("AAAsd", "movie description: $movieList")

            for (i in movieList.docs) {
                if (i.names.isNotEmpty() && i.description != null && i.genres.isNotEmpty() && i.poster.previewUrl != null) {
                    customList.add(
                        CustomDataClass(
                            bgImage = i.poster,
                            title = i.names[0].toString(),
                            description = i.description,
                            Rating = 8.1, // Добавьте значение для рейтинга здесь
                            Genre = i.genres
                        )
                    )
                } else {
                    Log.e("Error", "Some fields are null for movie ${i.names}")
                }
            }

            val customAdapter = CustomRecycleAdapter(customList)
            customRecyclerView.adapter = customAdapter

            val customSnapHelper: SnapHelper = PagerSnapHelper()
            customSnapHelper.attachToRecyclerView(customRecyclerView)
        }


    }
}

