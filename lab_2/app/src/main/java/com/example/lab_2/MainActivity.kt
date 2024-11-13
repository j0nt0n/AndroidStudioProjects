package com.example.lab_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerType: Spinner
    private lateinit var textViewDescription: TextView
    private lateinit var button: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerType = findViewById(R.id.spinnerPersons)
        textViewDescription = findViewById(R.id.textViewFind)
        button = findViewById(R.id.btnFindType)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.types,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter = adapter

        button.setOnClickListener {
            val position = spinnerType.selectedItemPosition
            val description = getDescriptionByPosition(position)
            textViewDescription.text = description
        }
    }

    private fun getDescriptionByPosition(position: Int): String {
        val descriptions = resources.getStringArray(R.array.description_types)
        return if (position in descriptions.indices) {
            descriptions[position]
        } else {
            ""
        }
    }
}