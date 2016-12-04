package com.mlshv.dayree.db

import android.content.Context
import com.mlshv.dayree.model.Record
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteException
import net.sqlcipher.database.SQLiteOpenHelper
import nl.qbusict.cupboard.CupboardFactory

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
    companion object {
        val dbName = "dayRee.db"
        val dbVersion = 1
        var dbInstance : SQLCipherDatabase? = null

        init {
            CupboardFactory.cupboard().register(Record::class.java)
        }

        fun closeDatabase() {
            dbInstance?.close()
            dbInstance = null
        }
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val sqlCipherDatabase = SQLCipherDatabase(sqLiteDatabase)
        CupboardFactory.cupboard().withDatabase(sqlCipherDatabase).createTables()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dbWrapper = SQLCipherDatabase(db)
        CupboardFactory.cupboard().withDatabase(dbWrapper).upgradeTables()
    }

    fun isDatabasePasswordCorrect(password: String): Boolean {
        val databasePath = context.getDatabasePath(dbName).path
        try {
            val sqliteDb = SQLiteDatabase.openDatabase(databasePath, password, null,
                    SQLiteDatabase.OPEN_READONLY)
            dbInstance = SQLCipherDatabase(sqliteDb)
        } catch (e: SQLiteException) { }

        return dbInstance != null
    }

    fun tryOpenDatabaseWithPassword(password: String): Boolean {
        val databasePath = context.getDatabasePath(dbName).path
        try {
            val sqliteDb = SQLiteDatabase.openDatabase(databasePath, password, null,
                    SQLiteDatabase.OPEN_READWRITE)
            dbInstance = SQLCipherDatabase(sqliteDb)
        } catch (e: SQLiteException) { }

        return dbInstance != null
    }

    fun put(any : Any) {
        if (CupboardFactory.cupboard().isRegisteredEntity(any.javaClass))
            CupboardFactory.cupboard().withDatabase(dbInstance).put(any)
    }

    fun isDatabaseExists() = context.getDatabasePath(dbName).exists()
}