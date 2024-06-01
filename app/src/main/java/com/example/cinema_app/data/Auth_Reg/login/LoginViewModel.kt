package com.example.kursovayz.screens.login


import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.R
import com.example.cinema_app.data.Auth_Reg.service.FirebaseRepository
import com.example.cinema_app.presentation.ProfileFragment
import com.example.kursovayz.screens.register.RegisterFragment
import com.example.kursovayz.screens.userinfo.UserInfoFragment
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    /**
     * Аутентификация пользователя.
     * @param email Электронная почта пользователя.
     * @param password Пароль пользователя.
     * @param context Контекст приложения.
     */
    fun loginUser(
        email: String,
        password: String,
        context: Context,
        parentFragmentManager: FragmentManager
    ) {
        viewModelScope.launch {
            FirebaseRepository().signInUser(email, password, context)
            val userInfoFragment = UserInfoFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, userInfoFragment)
                .commit()
        }
    }

}