package com.example.cinema_app.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cinema_app.data.Api.DataClasses.CustomDataClass
import com.example.cinema_app.data.CustomRecycleAdapter
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



private lateinit var customRecyclerView: RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.isItemActiveIndicatorEnabled = false
        bottomNavigation.itemPaddingBottom = 30


        // Use this to programmatically apply behavior attributes; eg.
        // standardBottomSheetBehavior.setState(STATE_EXPANDED);
        customRecyclerView = findViewById(R.id.CustomRecycleView)
        val doIt = doIt()
        doIt.fill(customRecyclerView,applicationContext)

    }

}

class doIt {
    fun fill(customRecyclerView: RecyclerView, applicationContext: Context) {
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
            movieList.docs[0].shortDescription
            Log.v("AAAsd", "movie description: $movieList")

            for (i in movieList.docs) {
                if (i.names.isNotEmpty() && i.shortDescription != null && i.genres.isNotEmpty() && i.poster.previewUrl != null && i.rating?.kp != null) {
                    customList.add(
                        CustomDataClass(
                            bgImage = i.poster,
                            title = i.names[0].toString(),
                            description = i.shortDescription,
                            Rating = i.rating.kp, // Добавьте значение для рейтинга здесь
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


