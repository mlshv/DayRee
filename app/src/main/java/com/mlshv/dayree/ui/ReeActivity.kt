package com.mlshv.dayree.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.mlshv.dayree.DayReeApplication

abstract class ReeActivity : AppCompatActivity() {

    private var childActivityOpened = false

    override fun onResume() {
        childActivityOpened = false
        if (DayReeApplication.isLocked()) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            this.startActivity(loginIntent)
        }
        super.onResume()
    }

    override fun onStop() {
        if (!childActivityOpened) {
            DayReeApplication.setLocked(true)
        }
        super.onStop()
    }

    override fun startActivity(intent: Intent?) {
        childActivityOpened = true
        super.startActivity(intent)
    }
}