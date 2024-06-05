// Ваша активность
package com.example.cinema_app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = VpAdapter(this, fragList)
        binding.vp2.adapter = adapter

        TabLayoutMediator(binding.tb, binding.vp2) { tab, pos ->
            tab.text = fragListTitles[pos]
        }.attach()
    }
}
