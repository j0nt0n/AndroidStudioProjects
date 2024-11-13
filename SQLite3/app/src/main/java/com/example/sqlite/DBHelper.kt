package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $DB_TABLE (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_PRICE INTEGER, " +
                "$COLUMN_COUNT INTEGER" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $DB_TABLE")
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "mydb"           // Имя базы данных
        const val DB_VERSION = 1             // Версия базы данных
        const val DB_TABLE = "goods"         // Имя таблицы

        const val COLUMN_ID = "id"           // Столбец для ID
        const val COLUMN_NAME = "name"       // Столбец для названия товара
        const val COLUMN_PRICE = "price"     // Столбец для цены
        const val COLUMN_COUNT = "count"     // Столбец для количества
    }
}
