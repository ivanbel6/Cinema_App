package com.example.cinema_app.presentation

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinema_app.R
import com.example.cinema_app.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)

        binding.userAgreement.setOnClickListener {
            showDownloadConfirmationDialog("https://drive.google.com/file/d/1hWJwkaFW9C-27WvVh_e1EyGZwILrv0Fs/view?usp=drive_link")
        }
        binding.userPolicy.setOnClickListener {
            showDownloadConfirmationDialog("https://drive.google.com/file/d/1hWJwkaFW9C-27WvVh_e1EyGZwILrv0Fs/view?usp=drive_link")
        }
        binding.userLicence.setOnClickListener {
            showDownloadConfirmationDialog("https://drive.google.com/file/d/1hWJwkaFW9C-27WvVh_e1EyGZwILrv0Fs/view?usp=drive_link")
        }
        binding.userRecomendations.setOnClickListener {
            showDownloadConfirmationDialog("https://drive.google.com/file/d/1hWJwkaFW9C-27WvVh_e1EyGZwILrv0Fs/view?usp=drive_link")
        }

        // И другие обработчики событий для других кнопок...

        binding.appBarLay.setNavigationOnClickListener {
            val profileFragment = ProfileFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, profileFragment)
                .commit()
        }

        return binding.root
    }

    private fun showDownloadConfirmationDialog(url: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Подтверждение загрузки")
            .setMessage("Вы уверены, что хотите скачать файл?")
            .setPositiveButton("Да") { _, _ ->
                downloadFileFromUrl(url)
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun downloadFileFromUrl(url: String) {
        val request = DownloadManager.Request(Uri.parse(url))
        request.setTitle("Загрузка файла")
        request.setDescription("Загрузка файла с $url")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "file_name.pdf")

        val downloadManager =
            requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}