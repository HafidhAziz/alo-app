package com.homework.mhafidhaziz.aloapp.presentation.homepage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.homework.mhafidhaziz.aloapp.R
import com.homework.mhafidhaziz.aloapp.databinding.FragmentHomePageBinding
import com.homework.mhafidhaziz.aloapp.entity.HomeList
import com.homework.mhafidhaziz.aloapp.event.HomeEvent
import com.homework.mhafidhaziz.aloapp.presentation.detail.DetailActivity
import com.homework.mhafidhaziz.aloapp.presentation.homepage.adapter.HomeListItemAdapter
import com.homework.mhafidhaziz.aloapp.presentation.mainpage.MainPageActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


/**
 * Created by mhafidhabdulaziz on 08/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class HomePageFragment : Fragment(),
    HomePageView {

    override lateinit var listAdapter: HomeListItemAdapter
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var databaseReference: DatabaseReference
    private var homeList: ArrayList<HomeList> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainPageActivity).setActionBarTitle(getString(R.string.app_name))

        EventBus.getDefault().register(this)

        val mVerticalLayoutManager = LinearLayoutManager(activity)
        mVerticalLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = mVerticalLayoutManager

        databaseReference = FirebaseDatabase.getInstance().reference.child("homelist")
        databaseReference.keepSynced(true)
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d(">>>", "onChildAdded:" + dataSnapshot.key!!)

                val model = dataSnapshot.getValue(HomeList::class.java)
                model?.let {
                    if (!homeList.contains(model)) {
                        homeList.add(it)
                    }
                }
                setAdapter()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d(">>>", "onChildChanged: ${dataSnapshot.key} " + previousChildName)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                Log.d(">>>", "onChildRemoved:" + dataSnapshot.key!!)
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d(">>>", "onChildMoved:" + dataSnapshot.key!!)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(">>>", "postComments:onCancelled", databaseError.toException())
            }
        }
        databaseReference.addChildEventListener(childEventListener)
    }

    private fun setAdapter() {
        listAdapter = HomeListItemAdapter(homeList)
        binding.recyclerView.adapter = listAdapter
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