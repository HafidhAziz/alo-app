package com.homework.mhafidhaziz.aloapp.presentation.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homework.mhafidhaziz.aloapp.R
import com.homework.mhafidhaziz.aloapp.databinding.FragmentProfileBinding
import com.homework.mhafidhaziz.aloapp.presentation.mainpage.MainPageActivity


/**
 * Created by mhafidhabdulaziz on 08/02/19.
 * alo-app
 * help.aziz@gmail.com
 * Copyright 2019
 */
class ProfileFragment : Fragment(),
    ProfileView {

    lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainPageActivity).setActionBarTitle(getString(R.string.menu_profile))
        binding.view = this
        binding.vm = ProfileViewModel()
        viewModel = binding.vm
    }
}