package com.example.cinema_app.data.TestRegister.service

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()
    //Туту нет запросов
    fun createUser(email: String, password: String, context: Context) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "OK!!", Toast.LENGTH_SHORT).show()
                    navigateToStartFragmentFromRegister(context)
                } else {
                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signInUser(email: String, password: String, context: Context) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "OK!!", Toast.LENGTH_SHORT).show()
                    navigateToStartFragment(context)
                } else {
                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun signInWithGoogle(credentials: AuthCredential, context: Context) {
        auth.signInWithCredential(credentials)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Logged in successfully", Toast.LENGTH_SHORT).show()
                    navigateToStartFragment(context)
                } else {
                    Toast.makeText(context, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun logOut() {
        Firebase.auth.signOut()
    }

    private fun navigateToStartFragment(context: Context) {
        val navController = NavHostFragment.findNavController((context as AppCompatActivity).supportFragmentManager.fragments[0])
//        navController.navigate(R.id.action_loginFragment_to_thirdFragment)
    }
    private fun navigateToStartFragmentFromRegister(context: Context) {
        val navController = NavHostFragment.findNavController((context as AppCompatActivity).supportFragmentManager.fragments[0])
//        navController.navigate(R.id.action_registerFragment_to_startFragment)
    }
}