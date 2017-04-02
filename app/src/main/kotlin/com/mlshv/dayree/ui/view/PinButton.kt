package com.mlshv.dayree.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import com.mlshv.dayree.ui.PinActivity


class PinButton(pinActivityContext: Context,
                attrs: AttributeSet? = null,
                defStyleAttr: Int = 0,
                defStyleRes: Int = 0) : Button(pinActivityContext, attrs, defStyleAttr, defStyleRes) {
    companion object {
        public val DELETE_KEY = -1
    }

    init {
        val parentActivity = this.context as PinActivity
        this.setOnClickListener {
            val btnValue = this.text.toString()
            val btnValueAsInt = if (btnValue != "~") btnValue.toInt() else DELETE_KEY
            parentActivity.onPinButtonClick(btnValueAsInt)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            this.isPressed = true
        }
        return super.onTouchEvent(event)
    }

    constructor(context: Context) : this(context, null, 0, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
}