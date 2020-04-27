package com.anesabml.quotey.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.anesabml.quotey.R
import com.anesabml.quotey.databinding.ActivityMainBinding
import com.anesabml.quotey.ui.favorites.FavoriteQuotesFragment
import com.anesabml.quotey.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var favoriteQuotesFragment: FavoriteQuotesFragment
    private lateinit var mainFragment: MainFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        initFragments()

        binding.bottomNavView.setOnNavigationItemSelectedListener(this)
        binding.bottomNavView.selectedItemId = R.id.home

    }

    private fun initFragments() {
        favoriteQuotesFragment = FavoriteQuotesFragment.newInstance()
        mainFragment = MainFragment.newInstance()
        settingsFragment = SettingsFragment()

        currentFragment = mainFragment
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var title = ""
        var showTitle = false
        when (item.itemId) {
            R.id.favorites -> {
                title = getString(R.string.favorites)
                showTitle = true
                currentFragment = favoriteQuotesFragment
            }
            R.id.home -> {
                showTitle = false
                currentFragment = mainFragment
            }
            R.id.settings -> {
                title = getString(R.string.settings)
                showTitle = true
                currentFragment = settingsFragment
            }
        }
        binding.title.text = title
        binding.title.visibility = if (showTitle) View.VISIBLE else View.GONE
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, currentFragment)
            .commitNow()
        return true
    }
}
