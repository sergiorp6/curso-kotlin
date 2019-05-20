package es.pue.calculadora

object Calculator {
    fun sum(operand1: Int, operand2: Int): Int = operand1 + operand2

    fun substract(operand1: Int, operand2: Int): Int = operand1 - operand2

    fun multiply(operand1: Int, operand2: Int): Int = operand1 * operand2

    fun divide(dividend: Int, divider: Int): Int = dividend / divider
}