package com.mlshv.dayree.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mlshv.dayree.R
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager
import com.mlshv.dayree.DayReeApplication

class MainActivity : AppCompatActivity() {

    var viewPager : AHBottomNavigationViewPager? = null
    var floatingActionButton : FloatingActionButton? = null
    var bottomNavigation : AHBottomNavigation? = null
    var bottomNavigationClickPerformed: Boolean = false // dirty hack to prevent callback loop
    var childActivityOpened = false

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()
        initViewPager()
        initFloatingActionButton()
    }

    override fun onResume() {
        childActivityOpened = false
        if (DayReeApplication.isLocked()) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            this.startActivity(loginIntent)
        }
        super.onResume()
    }

    private fun initBottomNavigation() {
        val tabColors = applicationContext.resources.getIntArray(R.array.tab_colors)
        bottomNavigation = findViewById(R.id.bottom_navigation) as AHBottomNavigation
        bottomNavigation!!.isColored = true
        val navigationAdapter = AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu)
        navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors)
        
        bottomNavigation!!.setOnTabSelectedListener { tabPosition, wasSelected ->
            bottomNavigationClickPerformed = true
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
                if (!bottomNavigationClickPerformed) { // don't wanna set bottom navigation if it has just changed a tab (prevents callback loop)
                    bottomNavigation!!.currentItem = position
                }
                reshowFab()
                bottomNavigationClickPerformed = false
            }
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })
    }

    private fun initFloatingActionButton() {
        floatingActionButton = findViewById(R.id.fab) as FloatingActionButton
        floatingActionButton!!.setOnClickListener({
            startRecordActivity()
        })
    }

    private fun startRecordActivity() {
        val recordIntent = Intent(this, RecordActivity::class.java)
        startActivity(recordIntent)
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

    override fun onStop() {
        if (!childActivityOpened) {
            DayReeApplication.setLocked(true)
            Log.d(this.javaClass.canonicalName, "Set application locked")
        }
        super.onStop()
    }

    override fun startActivity(intent: Intent?) {
        childActivityOpened = true
        super.startActivity(intent)
    }
}