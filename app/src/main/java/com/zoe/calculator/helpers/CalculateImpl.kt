package com.zoe.calculator.helpers

val add = { x: Double, y: String -> x + y.toDouble() }
val minus = { x: Double, y: String -> x - y.toDouble() }
val multiply = { x: Double, y: String -> x * y.toDouble() }
val divide = { x: Double, y: String -> x / y.toDouble() }


class CalculateImpl(private val activity: CalculatorInterface) {

    private var inputDisplay: String = "0"
    private var recentOperation: String = "+"

    private var result: Double = 0.0

    private val operations = listOf("+", "-", "*", "/", "^", "%", "√")

    fun handleOperation(operation: String) {
        calculateAndFlush()
        if (operation in operations) {
            recentOperation = operation
        }
    }


    fun handleNum(number: String) {
        inputDisplay = inputDisplay.trimStart { it == '0' }
        inputDisplay += number
        flush(inputDisplay)
    }

    /**
     *
     */
    private fun calculateAndFlush() {
        fun f(compute: (Double, String) -> Double) = compute(result, inputDisplay)

        result = when (recentOperation) {
            "+" -> f(add)
            "-" -> f(minus)
            "*" -> f(multiply)
            "÷" -> f(divide)
            else -> throw RuntimeException()
        }
        flush(result.toString())
        inputDisplay = "0"
    }

    private fun flush(s: String) {
        activity.showNewNumber(s.trimZero())
    }

    private fun String.trimZero(): String {
        if (this.toDouble().toInt().toDouble() == this.toDouble()) {
            return this.toDouble().toInt().toString()
        }
        return this
    }
}