package com.sigmaboy.modul7

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.sigmaboy.modul7.DatabaseContract.HomeworkColumns.Companion.TABLE_NAME
import com.sigmaboy.modul7.DatabaseContract.HomeworkColumns.Companion._ID
import kotlin.jvm.Throws

class HomeworkHelper(context: Context){
    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object{
        private const val DATABASE_NAME = TABLE_NAME
        private var INSTANCE: HomeworkHelper? = null

        fun getInstance(context: Context): HomeworkHelper =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: HomeworkHelper(context)
            }
    }

    @Throws(SQLiteException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }

    fun close(){
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_NAME,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC",
            null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_NAME, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_NAME, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_NAME, "$_ID = '$id'", null)
    }
}