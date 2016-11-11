package com.mlshv.dayree.db

import android.content.Context
import com.mlshv.dayree.model.Record
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteOpenHelper
import nl.qbusict.cupboard.CupboardFactory

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
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
}