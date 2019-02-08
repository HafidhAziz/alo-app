package com.homework.mhafidhaziz.aloapp.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by mhafidhabdulaziz on 09/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
@Parcelize
class HomeList(
    var imgUrl: String? = "",
    var title: String? = ""
) : Parcelable