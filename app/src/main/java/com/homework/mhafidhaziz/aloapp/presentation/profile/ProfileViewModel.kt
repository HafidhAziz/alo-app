package com.homework.mhafidhaziz.aloapp.presentation.profile

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField


/**
 * Created by mhafidhabdulaziz on 08/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class ProfileViewModel : ViewModel(){
    var bTextMail = ObservableField<String>()
    var bTextGender = ObservableField<String>()
    var bTextPhone = ObservableField<String>()
}