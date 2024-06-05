package com.example.cinema_app.presentation

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cinema_app.R
import com.example.cinema_app.databinding.ActivityProfileBinding
import com.example.cinema_app.presentation.MainActivity
import com.example.cinema_app.presentation.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val profileFragment = ProfileFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_login, profileFragment)
            .commit()
    }
}