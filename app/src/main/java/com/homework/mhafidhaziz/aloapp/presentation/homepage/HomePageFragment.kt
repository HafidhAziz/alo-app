package com.homework.mhafidhaziz.aloapp.presentation.homepage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.homework.mhafidhaziz.aloapp.R
import com.homework.mhafidhaziz.aloapp.databinding.FragmentHomePageBinding
import com.homework.mhafidhaziz.aloapp.entity.HomeList
import com.homework.mhafidhaziz.aloapp.presentation.mainpage.MainPageActivity
import com.google.firebase.database.DatabaseError
import com.homework.mhafidhaziz.aloapp.event.HomeEvent
import com.homework.mhafidhaziz.aloapp.presentation.detail.DetailActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


/**
 * Created by mhafidhabdulaziz on 08/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var mAdapter: FirebaseRecyclerAdapter<HomeList, HomeListHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainPageActivity).setActionBarTitle(getString(R.string.app_name))

        databaseReference = FirebaseDatabase.getInstance().reference.child("homelist")
        databaseReference.keepSynced(true)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        EventBus.getDefault().register(this)

        attachRecyclerViewAdapter()
    }

    private fun attachRecyclerViewAdapter() {
        mAdapter = newAdapter()
        binding.recyclerView.adapter = mAdapter
    }

    private fun newAdapter(): FirebaseRecyclerAdapter<HomeList, HomeListHolder> {
        val options = FirebaseRecyclerOptions.Builder<HomeList>()
            .setQuery(databaseReference, HomeList::class.java)
            .setLifecycleOwner(this)
            .build()

        return object : FirebaseRecyclerAdapter<HomeList, HomeListHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListHolder {
                return HomeListHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_home_list, parent, false)
                )
            }

            override fun onBindViewHolder(holder: HomeListHolder, position: Int, model: HomeList) {
                holder.bind(model)
            }

            override fun onDataChanged() {
                Log.i("", "")
            }

            override fun onError(e: DatabaseError) {
                Snackbar.make(view!!, "Terjadi Kesalahan", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        databaseReference = FirebaseDatabase.getInstance().reference.child("homelist")
        databaseReference.keepSynced(true)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        attachRecyclerViewAdapter()
    }

    @Subscribe
    fun onClickFeature(event: HomeEvent) {
        DetailActivity.startThisActivity(context!!, event.selectedPosition)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}