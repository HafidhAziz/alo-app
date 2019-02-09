package com.homework.mhafidhaziz.aloapp.presentation.homepage.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.homework.mhafidhaziz.aloapp.R
import com.homework.mhafidhaziz.aloapp.databinding.ItemHomeListBinding
import com.homework.mhafidhaziz.aloapp.entity.HomeList
import com.homework.mhafidhaziz.aloapp.event.HomeEvent
import org.greenrobot.eventbus.EventBus


/**
 * Created by mhafidhabdulaziz on 09/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class HomeListItemAdapter(private var homeList: MutableList<HomeList>) :
    RecyclerView.Adapter<HomeListItemAdapter.HomeListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_home_list, parent, false)
        return HomeListHolder(view)
    }

    override fun onBindViewHolder(holder: HomeListHolder, position: Int) {
        holder.bindItem(homeList[position])
    }

    override fun getItemCount(): Int = homeList.size

    inner class HomeListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemHomeListBinding = DataBindingUtil.bind(itemView)

        fun bindItem(items: HomeList) {
            binding.title.text = items.title
            Glide.with(itemView).load(items.imgUrl).into(binding.image)

            itemView.setOnClickListener {
                EventBus.getDefault().post(HomeEvent(adapterPosition.toString()))
            }
        }
    }
}