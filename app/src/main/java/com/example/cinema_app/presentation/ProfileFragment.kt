package com.example.cinema_app.presentation

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinema_app.R
import com.example.cinema_app.databinding.FragmentProfileBinding
import com.example.kursovayz.screens.login.LoginFragment
import com.example.kursovayz.screens.userinfo.UserInfoFragment
import java.io.File

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Загрузка данных пользователя
        loadUserInfo()

        // Скрыть кнопку регистрации, если пользователь уже зарегистрирован
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        if (sharedPref.getBoolean("IS_REGISTERED", false)) {
            binding.subscribeButton.visibility = View.GONE
        }
        binding.userLogin
        binding.changeUserInfo.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, UserInfoFragment())
                .commit()
        }

        binding.bottomNavigationProfile.selectedItemId = R.id.BottomNavProfile
        binding.aboutButton.setOnClickListener{
            val subscribeBtn = SubscribeFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, subscribeBtn)
                .commit()
        }
        binding.subscribeButton.setOnClickListener {
            val loginFragment = LoginFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, loginFragment)
                .commit()
        }
        binding.playlistsButton.setOnClickListener {
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
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container_login, SearchFragment())
                        .commit()
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

    private fun loadUserInfo() {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val userName = sharedPref.getString("USER_NAME", "Default Name")
        val avatarPath = sharedPref.getString("AVATAR_PATH", "")
        val userEmail = sharedPref.getString("USER_EMAIL", "No Email")

        binding.userName.text = userName
        binding.userLogin.text = userEmail

        if (avatarPath != null && avatarPath.isNotEmpty()) {
            val file = File(avatarPath)
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                binding.userAvatar.setImageBitmap(bitmap)
            } else {
                binding.userAvatar.setImageResource(R.drawable.svg_profile_avatar) // Замените на ваш ресурс по умолчанию
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
