package com.ginamelinia.architectureplayground.page.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ginamelinia.architectureplayground.R
import com.ginamelinia.architectureplayground.databinding.ActivityLoginBinding
import com.ginamelinia.architectureplayground.page.MainActivity
import com.ginamelinia.architectureplayground.repository.local.LocalRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(
                    LocalRepository(
                        getSharedPreferences(
                            LocalRepository.PREF_NAME, MODE_PRIVATE))) as T
            }
        }.create(LoginViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding?.lifecycleOwner = this

        binding?.viewModel = viewModel
        binding?.view = binding?.root

        viewModel?.authentication?.observe(this) { token ->
            if (token.isNotEmpty() && token.isNotBlank()) {
                viewModel?.saveToken(token)
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            }
        }

        viewModel?.error?.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel?.checkLogin()
    }
}