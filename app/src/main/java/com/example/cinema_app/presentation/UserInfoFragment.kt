package com.example.kursovayz.screens.userinfo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinema_app.R
import com.example.cinema_app.databinding.FragmentUserInfoBinding
import com.example.cinema_app.presentation.ProfileFragment

class UserInfoFragment : Fragment() {
    private lateinit var binding: FragmentUserInfoBinding
    private var selectedImageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnChooseImage.setOnClickListener {
            openImageChooser()
        }

        binding.saveButton.setOnClickListener {
            val userName = binding.etUserName.text.toString()
            saveUserInfo(userName, selectedImageUri?.toString() ?: "")
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, ProfileFragment())
                .commit()
        }
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            binding.imageView.setImageURI(selectedImageUri)
        }
    }

    private fun saveUserInfo(userName: String, avatarUrl: String) {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("USER_NAME", userName)
            putString("AVATAR_URL", avatarUrl)
            putBoolean("IS_REGISTERED", true)
            apply()
        }
    }
}
