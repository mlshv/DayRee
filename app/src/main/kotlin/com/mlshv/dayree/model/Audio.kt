package com.mlshv.dayree.model

import java.text.SimpleDateFormat
import java.util.*

data class Audio(val id : Long = 0,
            val title : String = "",
            val filename : String = "",
            val createdAt : String = dateFormat.format(Date()),
            val updatedAt: String = dateFormat.format(Date())) {
    companion object {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    }
}