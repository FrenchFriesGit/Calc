package com.frenchfriesgit.android.calc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = ""
    private var result = 0.0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val button1: Button = findViewById(R.id.button_1)
        val button2: Button = findViewById(R.id.button_2)
        val button3: Button = findViewById(R.id.button_3)
        val button4: Button = findViewById(R.id.button_4)
        val button5: Button = findViewById(R.id.button_5)
        val button6: Button = findViewById(R.id.button_6)
        val button7: Button = findViewById(R.id.button_7)
        val button8: Button = findViewById(R.id.button_8)
        val button9: Button = findViewById(R.id.button_9)
        val button0: Button = findViewById(R.id.button_0)

        val buttonAdd: Button = findViewById(R.id.button_add)
        val buttonSubtract: Button = findViewById(R.id.button_subtract)
        val buttonMultiply: Button = findViewById(R.id.button_multiply)
        val buttonDivide: Button = findViewById(R.id.button_divide)
        val buttonEqual: Button = findViewById(R.id.button_equal)
        val buttonClear: Button = findViewById(R.id.button_clear)

        // 数字ボタンのクリックリスナー
        val numberButtons = listOf(button1, button2, button3, button4, button5, button6, button7, button8, button9, button0)
        numberButtons.forEach { button ->
            button.setOnClickListener { onNumberClicked(button.text.toString()) }
        }

        // 演算子ボタンのクリックリスナー
        buttonAdd.setOnClickListener { onOperatorClicked("+") }
        buttonSubtract.setOnClickListener { onOperatorClicked("-") }
        buttonMultiply.setOnClickListener { onOperatorClicked("×") }
        buttonDivide.setOnClickListener { onOperatorClicked("÷") }

        // 計算の実行
        buttonEqual.setOnClickListener { onEqualClicked() }

        // クリアボタン
        buttonClear.setOnClickListener { onClearClicked() }
    }

    // 数字ボタンの処理
    private fun onNumberClicked(number: String) {
        currentInput += number
        display.text = currentInput
    }

    // 演算子ボタンの処理
    private fun onOperatorClicked(op: String) {
        if (currentInput.isNotEmpty()) {
            result = currentInput.toDouble()
            currentInput = ""
        }
        operator = op
    }

    // 「=」ボタンの処理
    private fun onEqualClicked() {
        if (currentInput.isNotEmpty()) {
            val input = currentInput.toDouble()
            result = when (operator) {
                "+" -> result + input
                "-" -> result - input
                "×" -> result * input
                "÷" -> if (input != 0.0) result / input else {
                    display.text = "エラー"
                    return
                }
                else -> result
            }
            display.text = result.toString()
            currentInput = ""
            operator = ""
        }
    }

    // クリアボタンの処理
    private fun onClearClicked() {
        currentInput = ""
        result = 0.0
        operator = ""
        display.text = "0"
    }
}
