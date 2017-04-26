package com.mlshv.dayree.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class AudioListItem : LinearLayout {
    var audioDbId: Long = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
}