package com.example.kursovayz.screens.register

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.R
import com.example.cinema_app.data.Auth_Reg.service.FirebaseRepository
import com.example.cinema_app.presentation.ProfileFragment
import com.example.kursovayz.screens.login.LoginFragment
import com.example.kursovayz.screens.userinfo.UserInfoFragment
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    /**
     * Регистрация нового пользователя.
     * @param email Электронная почта пользователя.
     * @param password Пароль пользователя.
     * @param context Контекст приложения.
     */
    fun registerNewUser(
        email: String,
        password: String,
        context: Context,
        parentFragmentManager: FragmentManager
    ) {
        viewModelScope.launch {
            FirebaseRepository().createUser(email, password, context)
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, UserInfoFragment())
                .commit()
        }
    }



}