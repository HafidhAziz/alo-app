package com.homework.mhafidhaziz.aloapp.presentation.mainpage

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.homework.mhafidhaziz.aloapp.R
import com.homework.mhafidhaziz.aloapp.databinding.ActivityMainPageBinding
import com.homework.mhafidhaziz.aloapp.presentation.homepage.HomePageFragment
import com.homework.mhafidhaziz.aloapp.presentation.profile.ProfileFragment

class MainPageActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainPageBinding
    private var firstTime = true

    companion object {

        fun startThisActivity(context: Context) {
            val intent = Intent(context, MainPageActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_page)
        binding.bottomTab.setOnNavigationItemSelectedListener(this)
        binding.bottomTab.selectedItemId = R.id.action_home

        binding.toolbar.let {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

    }

    private fun changeMainFragmentContent(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun getSelectedItem(bottomNavigationView: BottomNavigationView): Int {
        val menu = bottomNavigationView.menu
        for (i in 0 until bottomNavigationView.menu.size()) {
            val menuItem = menu.getItem(i)
            if (menuItem.isChecked) {
                return menuItem.itemId
            }
        }
        return 0
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_home -> {
                if (firstTime || (R.id.action_home != getSelectedItem(binding.bottomTab))) {
                    resetBottomMenuIcon()
                    firstTime = false
                    menuItem.setIcon(R.drawable.ic_home_active)
                    changeMainFragmentContent(HomePageFragment())
                }
            }
            R.id.action_profile -> {
                if (R.id.action_profile != getSelectedItem(binding.bottomTab)) {
                    resetBottomMenuIcon()
                    menuItem.setIcon(R.drawable.ic_home_active)
                    changeMainFragmentContent(ProfileFragment())
                }
            }
        }
        return true
    }

    private fun resetBottomMenuIcon() {
        val menu = binding.bottomTab.menu
        menu.findItem(R.id.action_home).setIcon(R.drawable.ic_home)
        menu.findItem(R.id.action_profile).setIcon(R.drawable.ic_home)
    }

    fun setActionBarTitle(title: String) {
        binding.titleName.text = title
    }
}
