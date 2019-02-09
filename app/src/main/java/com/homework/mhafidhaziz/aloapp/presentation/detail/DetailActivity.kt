package com.homework.mhafidhaziz.aloapp.presentation.detail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.homework.mhafidhaziz.aloapp.R
import com.homework.mhafidhaziz.aloapp.databinding.ActivityDetailBinding
import com.homework.mhafidhaziz.aloapp.databinding.ItemImageBinding
import com.homework.mhafidhaziz.aloapp.entity.ImageItem
import org.greenrobot.eventbus.EventBus


/**
 * Created by mhafidhabdulaziz on 08/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class DetailActivity : AppCompatActivity(),
    DetailView {

    lateinit var binding: ActivityDetailBinding
    private lateinit var databaseReference: DatabaseReference

    var inflater: LayoutInflater? = null
    private var imageList: ArrayList<ImageItem> = ArrayList()

    companion object {

        private const val EXTRA_SELECTED_POSITION = "EXTRA_SELECTED_POSITION"

        fun startThisActivity(context: Context, selectedPos: String?) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_SELECTED_POSITION, selectedPos)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.toolbar.let {
            binding.titleName.text = getString(R.string.title_detail)
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        databaseReference = FirebaseDatabase.getInstance().reference.child("homelist").child(intent.getStringExtra(EXTRA_SELECTED_POSITION)).child("images")
        databaseReference.keepSynced(true)
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d(">>>", "onChildAdded:" + dataSnapshot.key!!)

                val model = dataSnapshot.getValue(ImageItem::class.java)
                model?.let {
                    if (!imageList.contains(model)) {
                        imageList.add(it)
                    }
                }
                setPagerContent()
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

    private fun setPagerContent() {
        inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding.pagerImages.adapter = OnboardingAdapter()
        binding.indicator.setupWithViewPager(binding.pagerImages, true)
    }

    internal inner class OnboardingAdapter : PagerAdapter() {

        var binding: ItemImageBinding? = null

        override fun getCount(): Int {
            return imageList.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            inflater?.let {
                binding = DataBindingUtil.inflate(it, R.layout.item_image, container, false)
            }

            Glide.with(this@DetailActivity).load(imageList[position].detailImgUrl).into(binding?.imageSlider!!)

            (container as ViewPager).addView(binding?.root, 0)
            return binding?.root!!
        }

        override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
            return arg0 === arg1 as View
        }

        override fun destroyItem(container: ViewGroup, position: Int, objects: Any) {
            (container as ViewPager).removeView(objects as View)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId?.let {
            when (it) {
                android.R.id.home -> finish()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}