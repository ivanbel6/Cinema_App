package com.example.kursovayz.screens.userinfo

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.example.cinema_app.R
import com.example.cinema_app.databinding.FragmentUserInfoBinding
import com.example.cinema_app.presentation.ProfileFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.Calendar

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

        binding.changeBirthDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnChooseImage.setOnClickListener {
            openImageChooser()
        }

        binding.updateButton.setOnClickListener {
            val userName = binding.etUserName.text.toString()
            val email = binding.etEmail.text.toString()
            val avatarPath = selectedImageUri?.let { saveImageLocally(it) } ?: ""
            val gender = binding.spinnerGender.selectedItem.toString()
            val birthDay = binding.bithDate.text.split("-")[0].toInt()
            val birthMonth = binding.bithDate.text.split("-")[1].toInt()
            val birthYear = binding.bithDate.text.split("-")[2].toInt()
            val location = binding.spinnerLocation.selectedItem.toString()
            val additionalInfo = binding.etAdditionalInfo.text.toString()

            saveUserInfo(
                userName,
                email,
                avatarPath,
                gender,
                birthDay,
                birthMonth,
                birthYear,
                location,
                additionalInfo
            )
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, ProfileFragment())
                .commit()
        }

        loadUserInfo()
    }

    private fun showDatePickerDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_date_picker, null)
        val dayPicker = dialogView.findViewById<NumberPicker>(R.id.numberPickerDay)
        val monthPicker = dialogView.findViewById<NumberPicker>(R.id.numberPickerMonth)
        val yearPicker = dialogView.findViewById<NumberPicker>(R.id.numberPickerYear)

        // Initialize pickers
        dayPicker.minValue = 1
        dayPicker.maxValue = 31
        monthPicker.minValue = 1
        monthPicker.maxValue = 12
        yearPicker.minValue = 1900
        yearPicker.maxValue = Calendar.getInstance().get(Calendar.YEAR)

        val currentBirthDate = binding.bithDate.text.toString().split("-")
        dayPicker.value = currentBirthDate[0].toInt()
        monthPicker.value = currentBirthDate[1].toInt()
        yearPicker.value = currentBirthDate[2].toInt()

        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Select Birth Date")
            .setPositiveButton("OK") { _, _ ->
                val selectedDay = dayPicker.value
                val selectedMonth = monthPicker.value
                val selectedYear = yearPicker.value
                binding.bithDate.text = String.format("%02d-%02d-%04d", selectedDay, selectedMonth, selectedYear)
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
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

    private fun saveUserInfo(
        userName: String,
        email: String,
        avatarPath: String,
        gender: String,
        birthDay: Int,
        birthMonth: Int,
        birthYear: Int,
        location: String,
        additionalInfo: String
    ) {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("USER_NAME", userName)
            putString("EMAIL", email)
            putString("AVATAR_PATH", avatarPath)
            putString("GENDER", gender)
            putInt("BIRTH_DAY", birthDay)
            putInt("BIRTH_MONTH", birthMonth)
            putInt("BIRTH_YEAR", birthYear)
            putString("LOCATION", location)
            putString("ADDITIONAL_INFO", additionalInfo)
            putBoolean("IS_REGISTERED", true)
            apply()
        }
    }

    private fun loadUserInfo() {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
        binding.etUserName.setText(sharedPref.getString("USER_NAME", ""))
        binding.etEmail.setText(sharedPref.getString("EMAIL", ""))
        val avatarPath = sharedPref.getString("AVATAR_PATH", "")
        if (avatarPath != null && avatarPath.isNotEmpty()) {
            binding.imageView.setImageURI(Uri.fromFile(File(avatarPath)))
        }
        val gender = sharedPref.getString("GENDER", "Male")
        val genderIndex = resources.getStringArray(R.array.gender_array).indexOf(gender)
        if (genderIndex >= 0) {
            binding.spinnerGender.setSelection(genderIndex)
        }
        val birthDay = sharedPref.getInt("BIRTH_DAY", 1)
        val birthMonth = sharedPref.getInt("BIRTH_MONTH", 1)
        val birthYear = sharedPref.getInt("BIRTH_YEAR", 1900)
        binding.bithDate.text = String.format("%02d-%02d-%04d", birthDay, birthMonth, birthYear)
        val location = sharedPref.getString("LOCATION", "")
        val locationIndex = resources.getStringArray(R.array.country_array).indexOf(location)
        if (locationIndex >= 0) {
            binding.spinnerLocation.setSelection(locationIndex)
        }
        binding.etAdditionalInfo.setText(sharedPref.getString("ADDITIONAL_INFO", ""))
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
