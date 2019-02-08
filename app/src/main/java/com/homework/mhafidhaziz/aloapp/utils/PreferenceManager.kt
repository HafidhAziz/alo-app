package com.homework.mhafidhaziz.aloapp.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by mhafidhabdulaziz on 08/02/19.
 * alo-app
 * aziz.hfdh@gmail.com
 * Copyright 2018
 */
object PreferenceManager {
    private const val NAME = "com.homework.mhafidhaziz.aloapp"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val IS_USER_LOGIN = Pair("IS_USER_LOGIN_PREF", false)
    private val USER_DATA = Pair("USER_DATA_PREF", "")

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit()
     * and apply() ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isUserLogin: Boolean
        get() = preferences.getBoolean(IS_USER_LOGIN.first, IS_USER_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_USER_LOGIN.first, value)
        }

    var userData: String
        get() = preferences.getString(USER_DATA.first, USER_DATA.second)
        set(value) = preferences.edit {
            it.putString(USER_DATA.first, value)
        }
}