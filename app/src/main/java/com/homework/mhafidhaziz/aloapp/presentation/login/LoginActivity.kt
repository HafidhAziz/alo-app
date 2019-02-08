package com.homework.mhafidhaziz.aloapp.presentation.login

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.homework.mhafidhaziz.aloapp.R
import com.homework.mhafidhaziz.aloapp.databinding.ActivityLoginBinding
import com.homework.mhafidhaziz.aloapp.presentation.mainpage.MainPageActivity
import com.homework.mhafidhaziz.aloapp.utils.PreferenceManager
import com.homework.mhafidhaziz.aloapp.utils.ViewUtils


/**
 * Created by mhafidhabdulaziz on 08/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class LoginActivity : AppCompatActivity(),
    LoginView {

    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    companion object {

        fun startThisActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PreferenceManager.isUserLogin) {
            MainPageActivity.startThisActivity(this)
            finish()
            return
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.view = this
        binding.vm = LoginViewModel()
        viewModel = binding.vm
    }

    override fun onClickLogin(view: View) {
        var isValid = true

        viewModel.bTextUsernameError.set(null)
        if (TextUtils.isEmpty(viewModel.bTextUsername.get())) {
            isValid = false
            viewModel.bTextUsernameError.set(getString(R.string.error_empty_email))
        }

        viewModel.bTextPasswordError.set(null)
        if (TextUtils.isEmpty(viewModel.bTextPassword.get())) {
            isValid = false
            viewModel.bTextPasswordError.set(getString(R.string.error_empty_password))
        }

        if (isValid) {
            val userName = viewModel.bTextUsername.get()
            PreferenceManager.isUserLogin = true
            PreferenceManager.userData = userName
            ViewUtils.popKeyboard(this, view)
            MainPageActivity.startThisActivity(this)
            finish()
        }
    }
}