package com.homework.mhafidhaziz.aloapp.presentation

import android.app.Application
import com.homework.mhafidhaziz.aloapp.utils.PreferenceManager


/**
 * Created by mhafidhabdulaziz on 08/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class AloApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceManager.init(this)
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}