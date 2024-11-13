package com.example.sqlite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var db: DB
    private lateinit var etName: EditText
    private lateinit var etPrice: EditText
    private lateinit var etCount: EditText
    private lateinit var etId: EditText
    private lateinit var tvDb: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация базы данных
        db = DB(this)
        db.open()

        // Инициализация UI элементов
        etName = findViewById(R.id.etName)
        etPrice = findViewById(R.id.etPrice)
        etCount = findViewById(R.id.etCount)
        etId = findViewById(R.id.etId)
        tvDb = findViewById(R.id.tv_db)

        // Настройка кнопок
        findViewById<Button>(R.id.btnAdd).setOnClickListener { addRecord() }
        findViewById<Button>(R.id.btnRead).setOnClickListener { readRecords() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearFields() }
        findViewById<Button>(R.id.btnUpdate).setOnClickListener { updateRecord() }
        findViewById<Button>(R.id.btnDelete).setOnClickListener { deleteRecord() }
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close() // Закрыть базу данных при уничтожении Activity
    }

    private fun addRecord() {
        val name = etName.text.toString()
        val price = etPrice.text.toString().toIntOrNull() ?: 0
        val count = etCount.text.toString().toIntOrNull() ?: 0
        db.addRecord(name, price, count)
        readRecords()
    }

    private fun readRecords() {
        val cursor = db.getAllRecords()
        tvDb.text = "Товары:\n"

        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow(DBHelper.COLUMN_ID))
                val name = it.getString(it.getColumnIndexOrThrow(DBHelper.COLUMN_NAME))
                val price = it.getInt(it.getColumnIndexOrThrow(DBHelper.COLUMN_PRICE))
                val count = it.getInt(it.getColumnIndexOrThrow(DBHelper.COLUMN_COUNT))

                tvDb.append("ID=$id, name=$name, price=$price, count=$count\n")
            }
        }
    }

    private fun clearFields() {
        etName.text.clear()
        etPrice.text.clear()
        etCount.text.clear()
        etId.text.clear()
    }

    private fun updateRecord() {
        val id = etId.text.toString().toIntOrNull()
        if (id != null) {
            val name = etName.text.toString()
            val price = etPrice.text.toString().toIntOrNull() ?: 0
            val count = etCount.text.toString().toIntOrNull() ?: 0
            db.updateRecord(id, name, price, count)
            readRecords()
        }
    }

    private fun deleteRecord() {
        val id = etId.text.toString().toIntOrNull()
        if (id != null) {
            db.deleteRecord(id)
            readRecords()
        }
    }
}