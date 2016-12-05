package com.mlshv.dayree.db

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteException
import net.sqlcipher.database.SQLiteOpenHelper

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
    companion object {
        val dbName = "dayRee.db"
        val dbVersion = 1
        var dbInstance : SQLiteDatabase? = null

        fun closeDatabase() {
            dbInstance?.close()
            dbInstance = null
        }
    }

    fun tryOpenDatabaseWithPassword(password: String): Boolean {
        val databasePath = context.getDatabasePath(dbName).path
        try {
            dbInstance = SQLiteDatabase.openDatabase(databasePath, password, null,
                    SQLiteDatabase.OPEN_READWRITE)
        } catch (e: SQLiteException) { }

        return dbInstance != null
    }

    fun put(any : Any) {

    }

    fun isDatabaseExists() = context.getDatabasePath(dbName).exists()

    override fun onCreate(db: SQLiteDatabase?) {
        // TODO create tables
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }
}