package com.mlshv.dayree

import android.app.Application
import android.content.Context
import com.mlshv.dayree.util.DatabaseHelper
import net.sqlcipher.database.SQLiteDatabase
import java.util.*


class DayReeApplication : Application() {
    companion object {
        val reeLogTag: String
            inline get() = this::class.java.canonicalName
        var password = ""
        private val lockStatus = "LOCK_STATUS"
        private val saltStorage = "SALT_STORAGE"
        private lateinit var singleton: DayReeApplication

        fun getSalt(): ByteArray {
            val storage = singleton.getSharedPreferences(saltStorage, Context.MODE_PRIVATE)
            var salt = UUID.randomUUID().toString()
            if (storage.contains("SALT")) {
                salt = storage.getString("SALT", "")
            } else {
                storage.edit().putString("SALT", salt).apply()
            }
            return salt.toByteArray()
        }

        fun getInstance(): DayReeApplication {
            return singleton
        }

        fun setLocked(lock: Boolean) {
            singleton.getSharedPreferences(lockStatus, Context.MODE_PRIVATE)!!.edit().putBoolean("LOCK", lock).apply()
        }

        fun isLocked() = singleton.getSharedPreferences(lockStatus, Context.MODE_PRIVATE)!!.getBoolean("LOCK", true)
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

    fun createDatabaseWithPassword(password: String) = dbHelper.getWritableDatabase(password)!!

}