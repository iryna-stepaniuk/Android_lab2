package com.example.lab2

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

enum class Operations {
    OneDigitNumber, TwoDigitNumber, MoreThanTwoDigitNumber
}

class AnotherClass {
    companion object {
        fun showMessage(context: Context, text: String) {
            Toast.makeText(context, "The sum was calculated, $text", Toast.LENGTH_SHORT).show()
        }
    }

    data class NumbersForSum(val x: EditText, val y: EditText) {
        internal var firstNumber: EditText = x
        internal var secondNumber: EditText = y
    }

}

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var num1 = findViewById<EditText>(R.id.number1)
        var num2 = findViewById<EditText>(R.id.number2)

        val numbers = AnotherClass.NumbersForSum(num1, num2)
        num1 = numbers.secondNumber
        num2 = numbers.firstNumber


        val total = findViewById<EditText>(R.id.total)
        val calc = findViewById<Button>(R.id.button)

        fun action(n1: Int, n2: Int, operation: (Int, Int) -> Int): Int {
            return operation(n1, n2)
        }

        val add = { x: Int, y: Int -> x + y }
        val multiply = { x: Int, y: Int -> x * y }

        infix fun Int.customAdd(other: Int) = this + other

        fun add(a: Int, b: Int): Int {
            return a + b
        }

        fun add(a: Int, b: Int, c: Int): Int {
            return a + b + c
        }

        fun add(a: Int, b: Double): Double {
            return a + b
        }

        fun add(a: Double, b: Int): Double {
            return a + b
        }

        calc.setOnClickListener {

            var val1 = if (num1.text.toString().isEmpty()) 0 else num1.text.toString().toInt()

            var val2 = if (num2.text.toString().isEmpty()) 0 else num2.text.toString().toInt()

            val1 = val2.also { val2 = val1 }

            action(val1, 0, add)
            action(val2, 1, multiply)

            add(val1, 0)
            add(val2, 0.0)
            add(0.0, val1)
            add(0, 0, val2)

            val result = when (val1 customAdd val2) {
                in 0..9 -> "result:" + (val1 customAdd val2)
                in 10..99 -> "result: " + (val1 customAdd val2) / 10 + " " + (val1 customAdd val2) % 10
                else -> "result:" + (val1 customAdd val2)
            }

            val text = when (val1 customAdd val2) {
                in 0..9 -> Operations.OneDigitNumber.name
                in 10..99 -> Operations.TwoDigitNumber.name
                else -> Operations.MoreThanTwoDigitNumber.name
            }

            total.setText(result)
            AnotherClass.showMessage(this, text);
        }

    }
}