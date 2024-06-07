package com.example.cinema_app.presentation
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.data.adapters.CategoriesRecycleAdapter
import com.example.cinema_app.databinding.ActivitySearchBinding
import com.example.cinema_app.domain.UseCases.CreateCategoriesRecycleView
import com.example.cinema_app.domain.UseCases.SearchData
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class SearchActivity: AppCompatActivity(){
    private lateinit var binding: ActivitySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val searchFragment = SearchFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, searchFragment)
            .commit()

        binding.bottomNavigation.isItemActiveIndicatorEnabled = false
        binding.bottomNavigation.selectedItemId = R.id.BottomNavSearch
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.BottomNavHome -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.BottomNavSearch -> {
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

        if(SearchData.moviesList.size == 0)
            SearchData().prepare()



        fun getSearchQuery():String
        {
            return binding.searchLine.text.toString()
        }

    }
}