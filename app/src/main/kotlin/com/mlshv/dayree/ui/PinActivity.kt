package com.mlshv.dayree.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.mlshv.dayree.R
import com.mlshv.dayree.ui.view.PinButton

class PinActivity : AppCompatActivity() {
    private lateinit var pinCircles: Array<View>
    private var pinSequenceEntered = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
        initPinCircles()
    }

    fun initPinCircles() {
        pinCircles = arrayOf(
                findViewById(R.id.pin_circle1),
                findViewById(R.id.pin_circle2),
                findViewById(R.id.pin_circle3),
                findViewById(R.id.pin_circle4))
    }

    fun onPinButtonClick(digitEntered: Int) {
        Log.d("PinActivity", "Entered $digitEntered")
        if (digitEntered == PinButton.DELETE_KEY) {
            if (pinSequenceEntered.size > 0) pinSequenceEntered.removeAt(pinSequenceEntered.size - 1)
        } else {
            pinSequenceEntered.add(digitEntered)
        }
        updateCircles()
        if (pinSequenceEntered.size == 4) {
            // try login
        }
        Log.d("PinActivity", "Pin Sequence: $pinSequenceEntered")
    }

    private fun updateCircles() {
        pinCircles.forEachIndexed { index, view ->
            if (index < pinSequenceEntered.size) {
                (view as ImageView).setImageResource(R.drawable.pin_circle_active)
            } else {
                (view as ImageView).setImageResource(R.drawable.pin_circle)
            }
        }
    }
}
