package com.mlshv.dayree.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.mlshv.dayree.R
import android.support.design.widget.BottomNavigationView


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById(R.id.bottom_navigation) as BottomNavigationView

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_records -> {

                }
                R.id.action_audios -> {

                }
                R.id.action_photos -> {

                }
            }
            false
        }
    }

}