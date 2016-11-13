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
        init {
            CupboardFactory.cupboard().register(Record::class.java)
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
        var checkDB: SQLiteDatabase? = null
        val databasePath = context.getDatabasePath(dbName).path
        try {
            checkDB = SQLiteDatabase.openDatabase(databasePath, password, null,
                    SQLiteDatabase.OPEN_READONLY)
            checkDB!!.close()
        } catch (e: SQLiteException) {
            // database doesn't exist yet.
        }

        return checkDB != null
    }

    fun isDatabaseExists() = context.getDatabasePath(dbName).exists()
}