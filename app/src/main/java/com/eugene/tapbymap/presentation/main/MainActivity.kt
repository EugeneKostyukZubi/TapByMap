package com.eugene.tapbymap.presentation.main

import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.eugene.tapbymap.R
import com.eugene.tapbymap.base.BaseToolbarActivity
import com.eugene.tapbymap.navigation.MainNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseToolbarActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {

    override val contentView = R.layout.activity_main

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.map -> navigationController.navigate(MainNavigation.mapNavigationId)
            R.id.history -> navigationController.navigate(MainNavigation.historyNavigationId)
            else -> { /* Nothing to do here */
            }
        }
        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        /* Nothing to do here */
    }

    private lateinit var navigationController: NavController

    override fun initUi() {
        navigationController = findNavController(R.id.main_navigation_host)
        bottomNavigationBar.setOnNavigationItemSelectedListener(this)
        bottomNavigationBar.setOnNavigationItemReselectedListener(this)
    }
}