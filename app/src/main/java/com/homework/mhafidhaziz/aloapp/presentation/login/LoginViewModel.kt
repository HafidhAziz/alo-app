package com.homework.mhafidhaziz.aloapp.presentation.login

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField


/**
 * Created by mhafidhabdulaziz on 08/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class LoginViewModel : ViewModel() {
    var bTextUsernameError = ObservableField<String>()
    var bTextPasswordError = ObservableField<String>()
    var bTextUsername = ObservableField<String>()
    var bTextPassword = ObservableField<String>()
}