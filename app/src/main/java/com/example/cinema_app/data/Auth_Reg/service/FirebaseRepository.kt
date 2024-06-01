package com.example.cinema_app.data.Auth_Reg.service

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.cinema_app.R
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()

    fun createUser(email: String, password: String, context: Context) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "OK!!", Toast.LENGTH_SHORT).show()
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
//                    navigateToStartFragment(context)
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
//                    navigateToStartFragment(context)
                } else {
                    Toast.makeText(context, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun logOut() {
        Firebase.auth.signOut()
    }


}