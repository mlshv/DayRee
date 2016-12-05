package com.mlshv.dayree.db

import android.content.Context
import com.mlshv.dayree.model.Record
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteException
import net.sqlcipher.database.SQLiteOpenHelper
import android.content.ContentValues
import java.util.*

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
    companion object {
        val dbName = "dayRee.db"
        val dbVersion = 1
        var dbInstance : SQLiteDatabase? = null

        fun closeDatabase() {
            dbInstance?.close()
            dbInstance = null
        }

        fun putRecord(record : Record) : Long {
            val values = ContentValues()
            values.put("title", record.title)
            values.put("text", record.text)
            values.put("created_at", record.createdAt)
            values.put("updated_at", record.updatedAt)

            val recordId = dbInstance!!.insert("records", null, values)

            return recordId
        }

        fun getRecord(id : Long) : Record {
            val selectQuery = "SELECT  * FROM records WHERE id = $id"
            val c = dbInstance!!.rawQuery(selectQuery, null)
            c.moveToFirst()
            val result = Record(
                    id = c.getLong(c.getColumnIndex("id")),
                    title = c.getString(c.getColumnIndex("title")),
                    text = c.getString(c.getColumnIndex("text")),
                    createdAt = c.getString(c.getColumnIndex("created_at")),
                    updatedAt = c.getString(c.getColumnIndex("updated_at"))
            )
            c.close()
            return result
        }

        fun getAllRecords() : List<Record> {
            val result = ArrayList<Record>()
            val selectQuery = "SELECT  * FROM records"
            val c = dbInstance?.rawQuery(selectQuery, null) ?: return result
            if (c.moveToFirst()) {
                do {
                    val record = Record(
                            id = c.getLong(c.getColumnIndex("id")),
                            title = c.getString(c.getColumnIndex("title")),
                            text = c.getString(c.getColumnIndex("text")),
                            createdAt = c.getString(c.getColumnIndex("created_at")),
                            updatedAt = c.getString(c.getColumnIndex("updated_at")))
                    result.add(record)
                } while (c.moveToNext())
            }
            c.close()
            return result
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

    fun isDatabaseExists() = context.getDatabasePath(dbName).exists()

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE records (id INTEGER PRIMARY KEY, title TEXT, text TEXT, created_at DATETIME, updated_at DATETIME)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }
}