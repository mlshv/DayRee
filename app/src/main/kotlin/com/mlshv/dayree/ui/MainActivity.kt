package com.mlshv.dayree.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import com.mlshv.dayree.R
import com.mlshv.dayree.ui.fragment.AudiosFragment
import com.mlshv.dayree.ui.fragment.PhotosFragment
import com.mlshv.dayree.ui.fragment.RecordsFragment


class MainActivity : ReeActivity() {
    val fragments : Array<Fragment> = arrayOf(
            RecordsFragment(),
            AudiosFragment(),
            PhotosFragment())
    var currentFragment = fragments[0]
    lateinit var floatingActionButton : FloatingActionButton

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()
        initFragments()
        initFloatingActionButton()
        this.title = "DayRee"
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun initFloatingActionButton() {
        floatingActionButton = findViewById(R.id.fab) as FloatingActionButton
        floatingActionButton.setOnClickListener({ startRecordActivity() })
    }

    private fun initFragments() {
        val transaction = supportFragmentManager.beginTransaction()
        fragments.forEach { transaction.add(R.id.main_container, it).hide(it) }
        transaction.show(fragments[0])
        transaction.commit()
    }

    private fun initBottomNavigation() {
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        goToFragment(when (item.itemId) {
            R.id.navigation_records -> fragments[0]
            R.id.navigation_audios -> fragments[1]
            R.id.navigation_photos -> fragments[2]
            else -> throw IllegalStateException()
        })
        true
    }

    private fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .hide(currentFragment)
                .show(fragment)
                .commit()
        currentFragment = fragment
        reshowFab()
    }

    private fun startRecordActivity() {
        val recordIntent = Intent(this, RecordActivity::class.java)
        startActivity(recordIntent)
    }

    private fun reshowFab() = floatingActionButton.hide(fabChangeListener)

    val fabChangeListener = object : FloatingActionButton.OnVisibilityChangedListener() {
        override fun onHidden(fab: FloatingActionButton?) {
            super.onHidden(fab)
            fab?.show()
        }
    }
}