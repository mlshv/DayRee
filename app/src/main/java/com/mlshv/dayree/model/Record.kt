package com.mlshv.dayree.model

data class Record(val _id : Long = 0,
                  val title : String = "",
                  val text : String = "",
                  val creationTimestamp: Long = System.currentTimeMillis(),
                  val updateTimestamp: Long = System.currentTimeMillis())