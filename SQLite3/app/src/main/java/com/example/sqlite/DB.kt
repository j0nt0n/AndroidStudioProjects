package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DB(private val context: Context) {

    companion object {
        private const val DB_NAME = "mydb"
        private const val DB_VERSION = 1
        private const val DB_TABLE = "goods"

        // Названия колонок
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_COUNT = "count"
    }

    private var dbHelper: DBHelper? = null
    private var database: SQLiteDatabase? = null

    // Открыть подключение к базе данных
    fun open() {
        dbHelper = DBHelper(context)
        database = dbHelper?.writableDatabase
    }

    // Закрыть подключение к базе данных
    fun close() {
        dbHelper?.close()
    }

    // Добавление новой записи
    fun addRecord(name: String, price: Int, count: Int): Long {
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_PRICE, price)
            put(COLUMN_COUNT, count)
        }

        return try {
            database?.insertOrThrow(DB_TABLE, null, values) ?: -1
        } catch (e: SQLException) {
            Log.e("DB", "Error adding record", e)
            -1
        }
    }

    // Обновление существующей записи по ID
    fun updateRecord(id: Int, name: String, price: Int, count: Int): Int {
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_PRICE, price)
            put(COLUMN_COUNT, count)
        }

        return try {
            database?.update(DB_TABLE, values, "$COLUMN_ID = ?", arrayOf(id.toString())) ?: -1
        } catch (e: SQLException) {
            Log.e("DB", "Error updating record", e)
            -1
        }
    }

    // Удаление записи по ID
    fun deleteRecord(id: Int): Int {
        return try {
            database?.delete(DB_TABLE, "$COLUMN_ID = ?", arrayOf(id.toString())) ?: -1
        } catch (e: SQLException) {
            Log.e("DB", "Error deleting record", e)
            -1
        }
    }

    // Удаление всех записей
    fun deleteAllRecords() {
        try {
            database?.delete(DB_TABLE, null, null)
        } catch (e: SQLException) {
            Log.e("DB", "Error deleting all records", e)
        }
    }

    // Получение всех записей
    fun getAllRecords(): Cursor? {
        return database?.query(DB_TABLE, null, null, null, null, null, null)
    }

    // Получение записей по имени товара
    fun getRecordsByName(name: String): Cursor? {
        return database?.query(DB_TABLE, null, "$COLUMN_NAME = ?", arrayOf(name), null, null, null)
    }

    // Получение записей по количеству
    fun getRecordsByCount(count: Int): Cursor? {
        return database?.query(DB_TABLE, null, "$COLUMN_COUNT = ?", arrayOf(count.toString()), null, null, null)
    }

    // Метод для добавления тестовых данных
    fun populateTestData() {
        for (i in 1..5) {
            addRecord("My good № $i", i * 100, i)
        }
    }
}
