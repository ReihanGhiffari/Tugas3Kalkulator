package com.example.kalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText
    private var operator = ""
    private var firstNumber = ""
    private var secondNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        // Set up number buttons
        val buttons = listOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8",
            R.id.btn9 to "9"
        )

        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                if (operator.isEmpty()) {
                    firstNumber += value
                    display.setText(firstNumber)
                } else {
                    secondNumber += value
                    display.setText(secondNumber)
                }
            }
        }

        // Set up operator buttons
        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("/") }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clear() }
        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculate() }
    }

    private fun setOperator(op: String) {
        if (firstNumber.isNotEmpty()) {
            operator = op
            display.setText("") // Clear display to enter the second number
        }
    }

    private fun clear() {
        firstNumber = ""
        secondNumber = ""
        operator = ""
        display.setText("")
    }

    private fun calculate() {
        if (firstNumber.isNotEmpty() && secondNumber.isNotEmpty()) {
            val result = when (operator) {
                "+" -> firstNumber.toDouble() + secondNumber.toDouble()
                "-" -> firstNumber.toDouble() - secondNumber.toDouble()
                "*" -> firstNumber.toDouble() * secondNumber.toDouble()
                "/" -> if (secondNumber != "0") {
                    firstNumber.toDouble() / secondNumber.toDouble()
                } else {
                    0.0 // Handle division by zero gracefully
                }
                else -> 0.0
            }

            display.setText(result.toString())
            // Update firstNumber to the result for continued calculations
            firstNumber = result.toString()
            secondNumber = "" // Reset second number and operator for new input
            operator = ""
        }
    }
}
