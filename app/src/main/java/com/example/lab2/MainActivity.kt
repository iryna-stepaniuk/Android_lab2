package com.example.lab2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val num1 = findViewById<EditText>(R.id.number1)
        val num2 = findViewById<EditText>(R.id.number2)

        val total = findViewById<EditText>(R.id.total)
        val calc = findViewById<Button>(R.id.button)

        infix fun Int.customAdd(other: Int) = this + other
        
        calc.setOnClickListener{

            var val1 = if (num1.text.toString().isEmpty()) 0 else num1.text.toString().toInt()

            var val2 = if (num2.text.toString().isEmpty()) 0 else num2.text.toString().toInt()

            val1 = val2.also { val2 = val1 }

            val result = when (val1 customAdd val2) {
                in 0..9 -> "result:" + (val1 customAdd val2)
                in 10..99 -> "result: " + (val1 customAdd val2) / 10 + " " + (val1 customAdd val2) % 10
                else -> "result:" + (val1 customAdd val2)
            }

            total.setText(result)

        }

    }
}