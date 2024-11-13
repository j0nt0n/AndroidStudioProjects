package com.example.lab_1
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app. AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.TextView
import android.widget.Button



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber1 = findViewById<EditText>(R.id.editTextText1)
        val editTextNumber2 = findViewById<EditText>(R.id.editTextText2)
        val textViewResult = findViewById<TextView>(R.id.textView)
        val buttonAdd = findViewById<Button>(R.id.button)
        val buttonSubtract = findViewById<Button>(R.id.button2)
        val buttonMultiply = findViewById<Button>(R.id.button3)
        val buttonDivide = findViewById<Button>(R.id.button4)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonAdd.setOnClickListener {
            try {
                val number1 = editTextNumber1.text.toString().toInt()
                val number2 = editTextNumber2.text.toString().toInt()
                val result = number1 + number2
                textViewResult.text = "Результат: $result"
            } catch (e: NumberFormatException) {
                textViewResult.text = "Пожалуйста, введите корректные числа"
            }
        }

        buttonSubtract.setOnClickListener {
            try {
                val number1 = editTextNumber1.text.toString().toInt()
                val number2 = editTextNumber2.text.toString().toInt()
                val result = number1 - number2
                textViewResult.text = "Результат: $result"
            } catch (e: NumberFormatException) {
                textViewResult.text = "Пожалуйста, введите корректные числа"
            }
        }

        buttonMultiply.setOnClickListener {
            try {
                val number1 = editTextNumber1.text.toString().toInt()
                val number2 = editTextNumber2.text.toString().toInt()
                val result = number1 * number2
                textViewResult.text = "Результат: $result"
            } catch (e: NumberFormatException) {
                textViewResult.text = "Пожалуйста, введите корректные числа"
            }
        }

        buttonDivide.setOnClickListener {
            try {
                val number1 = editTextNumber1.text.toString().toInt()
                val number2 = editTextNumber2.text.toString().toInt()
                if (number2 == 0) {
                    textViewResult.text = "Деление на ноль невозможно"
                } else {
                    val result = number1 / number2
                    textViewResult.text = "Результат: $result"
                }
            } catch (e: NumberFormatException) {
                textViewResult.text = "Пожалуйста, введите корректные числа"
            }
        }


    }
}