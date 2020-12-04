package com.zoe.calculator.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.zoe.calculator.R
import com.zoe.calculator.helpers.CalculateImpl
import com.zoe.calculator.helpers.CalculatorInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CalculatorInterface {

    lateinit var calculate: CalculateImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculate = CalculateImpl(this)


        val operationsButtons = listOf<Button>(btn_plus, btn_minus, btn_multiply, btn_divide)
        val numberButtons =
            listOf<Button>(btn_decimal, btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9)


        randomNumber(numberButtons)


        operationsButtons.setButtonListenerByText(calculate::handleOperation)
        numberButtons.setButtonListenerByText(calculate::handleNum)

        btn_equals.setOnClickListener { calculate.handleEqual() }
        btn_clear.setOnClickListener { calculate.handleClear() }

    }


    private fun randomNumber(numbers: List<Button>) {
        val textOnButton: MutableList<CharSequence> = mutableListOf()
        numbers.mapTo(textOnButton) { button -> button.text }
        numbers.forEach {
            val r = textOnButton.random()
            it.text = r
            textOnButton -= r
        }
    }


    private fun List<Button>.setButtonListenerByText(method: (s: String) -> Unit) {
        this.forEach { btn ->
            btn.setOnClickListener {
                method(btn.text.toString())
            }
        }
    }


    override fun showNewNumber(value: String) {
        result.text = value
    }

}