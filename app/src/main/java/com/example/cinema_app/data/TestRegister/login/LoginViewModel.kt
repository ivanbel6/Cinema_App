package com.example.kursovayz.screens.login


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.data.TestRegister.service.FirebaseRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    /**
     * Аутентификация пользователя.
     * @param email Электронная почта пользователя.
     * @param password Пароль пользователя.
     * @param context Контекст приложения.
     */
    fun loginUser(email: String, password: String, context: Context) {
        viewModelScope.launch {
            FirebaseRepository().signInUser(email, password, context)
        }
    }

}