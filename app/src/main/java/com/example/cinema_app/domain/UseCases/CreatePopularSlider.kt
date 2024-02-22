package com.example.cinema_app.domain.UseCases

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cinema_app.data.Api.DataClasses.CustomDataClass
import com.example.cinema_app.data.Api.Interface.ApiInterface
import com.example.cinema_app.data.CustomRecycleAdapter
import com.example.cinema_app.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreatePopularSlider {
    fun fillPopularSlider(customRecyclerView: RecyclerView, applicationContext: Context) {
        customRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        val apiInterface = MainActivity.retrofit.create(ApiInterface::class.java)
        val customList: MutableList<CustomDataClass> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val movieList = apiInterface.getMoviesPopular()
            for (i in movieList.docs) {
                if (i.names.isNotEmpty() && i.description != null && i.genres.isNotEmpty() && i.poster.previewUrl != null && i.rating?.kp != null) {
                    customList.add(
                        CustomDataClass(
                            bgImage = i.poster,
                            title = i.names[0].name,
                            description = i.description,
                            Rating = i.rating.kp,
                            Genre = i.genres.toString()
                                .replace(Regex("[name|Genre|\\[|\\]|\\(|\\)|=]"), "")
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