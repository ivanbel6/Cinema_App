package com.example.cinema_app.presentation

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
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
            binding.profileSignIn.visibility = View.GONE
            binding.visibleUserInfo.visibility = View.VISIBLE
        }

        binding.helpButton.setOnClickListener{
            replaceFragment(HelpFragment())
        }
        binding.aboutButton.setOnClickListener{
            replaceFragment(AboutFragment())
        }
        binding.changeUserInfo.setOnClickListener {
            replaceFragment(UserInfoFragment())
        }

        binding.bottomNavigationProfile.selectedItemId = R.id.BottomNavProfile
        binding.subscribeButton.setOnClickListener{
            replaceFragment(SubscribeFragment())
        }
        binding.profileSignIn.setOnClickListener {
            replaceFragment(LoginFragment())
        }
        binding.playlistsButton.setOnClickListener {
            replaceFragment(PlayListFragment())
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
                    val intent = Intent(requireContext(), SearchActivity::class.java)
                    startActivity(intent)
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

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_login, fragment)
            .commit()
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
