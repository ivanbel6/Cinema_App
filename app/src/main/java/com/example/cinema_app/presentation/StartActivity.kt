package com.example.cinema_app.presentation

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cinema_app.R
import com.example.cinema_app.data.adapters.VpAdapter
import com.example.cinema_app.databinding.ActivityStartBinding
import com.google.android.material.tabs.TabLayoutMediator

class StartActivity : AppCompatActivity() {

    private val fragList = listOf(
        FirstFragment.newInstance(),
        SecondFragment.newInstance(),
        ThirdFragment.newInstance()
    )
    private val fragListTitles = listOf("First", "Second", "Third")
    private lateinit var binding: ActivityStartBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("com.example.cinema_app", MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

        // Если это не первый запуск, перенаправляем на MainActivity
        if (!isFirstLaunch) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Закрываем текущую активность
            return
        }

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = VpAdapter(this, fragList)
        binding.vp2.adapter = adapter

        TabLayoutMediator(binding.tb, binding.vp2) { tab, pos ->
            tab.text = fragListTitles[pos]
        }.attach()

        // Сохраняем состояние первого запуска
        with(sharedPreferences.edit()) {
            putBoolean("isFirstLaunch", false)
            apply()
        }
    }
}
