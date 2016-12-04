package com.mlshv.dayree.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.mlshv.dayree.R
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager




class MainActivity : AppCompatActivity() {

    var viewPager : AHBottomNavigationViewPager? = null
    var floatingActionButton : FloatingActionButton? = null
    var bottomNavigation : AHBottomNavigation? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()
        initViewPager()
        initFloatingActionButton()
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

    private fun initViewPager() {
        viewPager = findViewById(R.id.main_pager) as AHBottomNavigationViewPager
        viewPager!!.setPagingEnabled(true)
        val pagerAdapter = MainPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = pagerAdapter
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                bottomNavigation!!.currentItem = position
                reshowFab()
            }
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })
    }

    private fun initFloatingActionButton() {
        floatingActionButton = findViewById(R.id.fab) as FloatingActionButton
    }

    private fun reshowFab() {
        floatingActionButton?.hide(fabChangeListener)
    }

    val fabChangeListener = object : FloatingActionButton.OnVisibilityChangedListener() {
        private fun getColorForCurrentTabPosition() : Int {
            val tabPosition = viewPager!!.currentItem
            when(tabPosition) {
                0 -> return R.color.color_tab_0
                1 -> return R.color.color_tab_1
                2 -> return R.color.color_tab_2
            }
            null!!
        }

        override fun onHidden(fab: FloatingActionButton?) {
            super.onHidden(fab)
            fab!!.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@MainActivity, getColorForCurrentTabPosition()))
            fab.show()
        }
    }
}