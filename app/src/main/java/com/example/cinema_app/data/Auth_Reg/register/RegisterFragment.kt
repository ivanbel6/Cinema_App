package com.example.kursovayz.screens.register

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cinema_app.R
import com.example.cinema_app.databinding.FragmentRegisterBinding
import com.example.kursovayz.screens.login.LoginFragment

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    companion object {
        const val HTTP_METHOD_GET = "GET"
        const val HTTP_METHOD_POST = "POST"
        const val HTTP_METHOD_PUT = "PUT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    /**
     * Клик на кнопку "Зарегистрироваться".
     * Выполняет POST-запрос для создания нового пользователя.
     */
    override fun onStart() {
        super.onStart()
        binding.btnRegister.setOnClickListener {
            val password = binding.etPassword.text.toString()
            val passwordConfirm = binding.etPasswordconfirm.text.toString()
            val userName = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            if (password.isNotEmpty() && passwordConfirm.isNotEmpty() && userName.isNotEmpty() && email.isNotEmpty()){
                if (password.equals(passwordConfirm)) {
                    val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("USER_EMAIL", email)
                        apply()
                    }
                    viewModel.registerNewUser(
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString(),
                        requireContext(),
                        parentFragmentManager
                    )
                }else{
                    Toast.makeText(requireContext(),"Пароли не совпадают",Toast.LENGTH_SHORT).show()
                    binding.etPassword.setText("")
                    binding.etPasswordconfirm.setText("")
                }
            }else{
                Toast.makeText(requireContext(),"Одно или несколько полей пусты",Toast.LENGTH_SHORT).show()
            }


        }

        binding.tvLogin.setOnClickListener {
            val loginFragment = LoginFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, loginFragment)
                .commit()
        }

    }
}