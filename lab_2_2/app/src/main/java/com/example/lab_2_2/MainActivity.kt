package com.example.lab_2_2

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn = findViewById<Button>(R.id.fineButton)
        val collor_text = findViewById<Spinner>(R.id.spinner)
        val collor_button = findViewById<Spinner>(R.id.spinner2)
        btn.setOnClickListener {
            btn.setBackgroundColor(Color.parseColor(collor_button.selectedItem.toString()))
            btn.setTextColor(Color.parseColor(collor_text.selectedItem.toString()))
        }

    }
}

