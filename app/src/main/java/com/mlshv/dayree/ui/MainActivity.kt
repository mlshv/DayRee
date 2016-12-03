package com.mlshv.dayree.ui

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.mlshv.dayree.R
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()

        val viewPager = findViewById(R.id.main_pager) as ViewPager
        val pagerAdapter = MainPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
    }

    private fun initBottomNavigation() {
        val tabColors = applicationContext.resources.getIntArray(R.array.tab_colors)
        val bottomNavigation = findViewById(R.id.bottom_navigation) as AHBottomNavigation
        bottomNavigation.isColored = true
        val navigationAdapter = AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu)
        navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors)
        
        bottomNavigation.setOnTabSelectedListener { tabPosition, wasSelected ->
            when(tabPosition) {
                0 -> {
                    Toast.makeText(this, "Tab 1", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    Toast.makeText(this, "Tab 2", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    Toast.makeText(this, "Tab 3", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}