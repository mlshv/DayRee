package com.mlshv.dayree

import android.app.Application
import com.mlshv.dayree.db.DatabaseHelper
import net.sqlcipher.database.SQLiteDatabase

class DayReeApplication : Application() {
    companion object {
        private var singleton: DayReeApplication? = null
        fun getInstance(): DayReeApplication {
            return singleton!!
        }
    }

    private val dbHelper = DatabaseHelper(this)

    override fun onCreate() {
        super.onCreate()
        singleton = this
        // SQLCipher initialization
        SQLiteDatabase.loadLibs(this)
    }

    fun isDatabaseExists() = dbHelper.isDatabaseExists()

    fun isPasswordCorrect(password: String) = dbHelper.tryOpenDatabaseWithPassword(password)

    fun createDatabaseWithPassword(password: String) {
        val db = dbHelper.getWritableDatabase(password)
    }
}