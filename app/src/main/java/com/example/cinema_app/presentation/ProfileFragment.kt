package com.example.cinema_app.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinema_app.R
import com.example.cinema_app.databinding.FragmentProfileBinding
import com.example.kursovayz.screens.login.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null // Привязка для фрагмента
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigationProfile.selectedItemId = R.id.BottomNavProfile
        binding.subscribeButton.setOnClickListener {
            val loginFragment = LoginFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, loginFragment)
                .commit()
        }
        binding.playlistsButton.setOnClickListener{
            val playListFragment = PlayListFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, playListFragment)
                .commit()
        }
        binding.bottomNavigationProfile.isItemActiveIndicatorEnabled = false
        binding.bottomNavigationProfile.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.BottomNavHome -> {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.BottomNavSearch -> {
                    //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SearchFragment()).commit()
                    true
                }

                R.id.BottomNavProfile -> {
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}