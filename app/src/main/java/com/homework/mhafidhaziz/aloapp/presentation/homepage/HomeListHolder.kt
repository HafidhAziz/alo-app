package com.homework.mhafidhaziz.aloapp.presentation.homepage

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.homework.mhafidhaziz.aloapp.R
import com.homework.mhafidhaziz.aloapp.entity.HomeList
import com.homework.mhafidhaziz.aloapp.event.HomeEvent
import org.greenrobot.eventbus.EventBus


/**
 * Created by mhafidhabdulaziz on 09/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class HomeListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mTitle: TextView = itemView.findViewById(R.id.title)
    private val mImage: ImageView = itemView.findViewById(R.id.image)

    fun bind(homeList: HomeList) {

        setTitle(homeList.title)
        setImage(homeList.imgUrl)

        itemView.setOnClickListener {
            EventBus.getDefault().post(HomeEvent(adapterPosition.toString()))
        }
    }

    private fun setTitle(title: String?) {
        mTitle.text = title
    }

    private fun setImage(imgUrl: String?) {
        Glide.with(itemView).load(imgUrl).into(mImage)
    }
}