package com.mlshv.dayree.ui

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.mlshv.dayree.R
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager


class MainActivity : AppCompatActivity() {

    var viewPager : AHBottomNavigationViewPager? = null
    var bottomNavigation : AHBottomNavigation? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()

        viewPager = findViewById(R.id.main_pager) as AHBottomNavigationViewPager
        viewPager!!.setPagingEnabled(true)
        val pagerAdapter = MainPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = pagerAdapter
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                bottomNavigation!!.currentItem = position
            }
        })
    }

    private fun initBottomNavigation() {
        val tabColors = applicationContext.resources.getIntArray(R.array.tab_colors)
        bottomNavigation = findViewById(R.id.bottom_navigation) as AHBottomNavigation
        bottomNavigation!!.isColored = true
        val navigationAdapter = AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu)
        navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors)
        
        bottomNavigation!!.setOnTabSelectedListener { tabPosition, wasSelected ->
            viewPager!!.currentItem = tabPosition
            true
        }
    }
}