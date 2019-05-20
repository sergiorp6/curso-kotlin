package es.pue.calculadora

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class CalculatorService : Service() {

    inner class CalculatorBinder : Binder() {
        val service: CalculatorService = this@CalculatorService
    }

    private val calculatorBinder: IBinder = CalculatorBinder()

    override fun onBind(intent: Intent): IBinder = calculatorBinder

    fun sum(operand1: Int, operand2: Int): Int = Calculator.sum(operand1, operand2)

    fun substract(operand1: Int, operand2: Int) = Calculator.substract(operand1, operand2)

    fun multiply(operand1: Int, operand2: Int) = Calculator.multiply(operand1, operand2)

    fun divide(dividend: Int, divider: Int) = Calculator.divide(dividend, divider)
}
