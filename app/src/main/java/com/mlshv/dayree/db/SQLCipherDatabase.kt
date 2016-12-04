package com.mlshv.dayree.db

import android.content.ContentValues
import net.sqlcipher.database.SQLiteDatabase
import nl.qbusict.cupboard.CupboardDatabase

class SQLCipherDatabase(val database : SQLiteDatabase) : CupboardDatabase {

    override fun replaceOrThrow(table: String?, nullColumnHack: String?, initialValues: ContentValues?) = database.replaceOrThrow(table, nullColumnHack, initialValues)

    override fun rawQuery(sql: String?, selectionArgs: Array<out String>?) = database.rawQuery(sql, selectionArgs)!!

    override fun setTransactionSuccessful() = database.setTransactionSuccessful()

    override fun query(distinct: Boolean, table: String?, columns: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, groupBy: String?, having: String?, orderBy: String?, limit: String?)
            = database.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)

    override fun endTransaction() = database.endTransaction()

    override fun inTransaction() = database.inTransaction()

    override fun beginTransaction() = database.beginTransaction()

    override fun update(table: String?, values: ContentValues?, whereClause: String?, whereArgs: Array<out String>?) = database.update(table, values, whereClause, whereArgs)

    override fun execSQL(sql: String?) = database.execSQL(sql)

    override fun insertOrThrow(table: String?, nullColumnHack: String?, values: ContentValues?) = database.insertOrThrow(table, nullColumnHack, values)

    override fun yieldIfContendedSafely() { database.yieldIfContendedSafely() }

    override fun delete(table: String?, whereClause: String?, whereArgs: Array<out String>?) = database.delete(table, whereClause, whereArgs)

    fun close() = database.close()
}
