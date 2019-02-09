package com.homework.mhafidhaziz.aloapp.presentation.homepage

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField


/**
 * Created by mhafidhabdulaziz on 09/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class HomePageViewModel : ViewModel() {
    var bIsLoading = ObservableField<Boolean>(true)
}