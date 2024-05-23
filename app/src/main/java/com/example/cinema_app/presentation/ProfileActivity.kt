package com.example.cinema_app.presentation.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cinema_app.R
import com.example.cinema_app.databinding.ActivityProfileBinding
import com.example.cinema_app.presentation.ProfileFragment


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val profileFragment = ProfileFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_login, profileFragment)
            .commit()

    }
}