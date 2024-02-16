package com.example.cinema_app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.domain.CreateAndFillCustomRecycleView
import com.google.android.material.bottomnavigation.BottomNavigationView


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
        val doIt = CreateAndFillCustomRecycleView()
        doIt.fill(customRecyclerView,applicationContext)

    }

}




