package com.example.kursovayz.screens.userinfo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinema_app.R
import com.example.cinema_app.databinding.FragmentUserInfoBinding
import com.example.cinema_app.presentation.ProfileFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

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
            val avatarPath = selectedImageUri?.let { saveImageLocally(it) } ?: ""
            saveUserInfo(userName, avatarPath)
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

    private fun saveUserInfo(userName: String, avatarPath: String) {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("USER_NAME", userName)
            putString("AVATAR_PATH", avatarPath)
            putBoolean("IS_REGISTERED", true)
            apply()
        }
    }

    private fun saveImageLocally(uri: Uri): String {
        val inputStream: InputStream? = requireContext().contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val directory = requireContext().getExternalFilesDir(null)
        val file = File(directory, "avatar.png")
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fileOutputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return file.absolutePath
    }
}
