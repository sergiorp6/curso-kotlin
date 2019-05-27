package es.pue.calculadora

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var service: CalculatorService
    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            this@MainActivity.service = (service as CalculatorService.CalculatorBinder).service
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberButtons = mapOf<Button, Int>(
            calc_btNumber0 to 0,
            calc_btNumber1 to 1,
            calc_btNumber2 to 2,
            calc_btNumber3 to 3,
            calc_btNumber4 to 4,
            calc_btNumber5 to 5,
            calc_btNumber6 to 6,
            calc_btNumber7 to 7,
            calc_btNumber8 to 8,
            calc_btNumber9 to 9
        )

        val operatorButtons = mapOf<Button, String>(
            calc_sumar to "+",
            calc_restar to "-",
            calc_multiplicar to "*",
            calc_dividir to "/"
        )

        numberButtons.forEach {
            it.key.setOnClickListener { _: View -> showOperandOnDisplay(it.value) }
        }

        operatorButtons.forEach {
            it.key.setOnClickListener { _: View -> showOperationOnDisplay(it.value) }
        }

        calc_btResult.setOnClickListener {
            showResultOnDisplay()
            diplayWebPageDependingOnResult()
        }

        calc_btC.setOnClickListener {
            resetTextViews()
        }

        startService((Intent(this, CalculatorService::class.java)))

        managePreferences()
    }

    private fun resetTextViews() {
        tv_operand1.text = ""
        tv_operand2.text = ""
        tv_operation.text = ""
        tv_result.text = ""
    }

    override fun onResume() {
        super.onResume()
        val i = Intent(this, CalculatorService::class.java)
        bindService(i, connection, 0)
    }

    override fun onPause() {
        super.onPause()
        unbindService(connection)
    }

    private fun showOperandOnDisplay(value: Int) {
        when {
            tv_operand1.text.isEmpty() -> tv_operand1.text = value.toString()
            tv_operand2.text.isEmpty() -> tv_operand2.text = value.toString()
            else -> {
                tv_operand1.text = value.toString()
                tv_operand2.text = ""
            }
        }
    }

    private fun showOperationOnDisplay(operation: String) {
        tv_operation.text = operation
    }

    private fun showResultOnDisplay() {
        tv_result.text = executeOperation(
            tv_operand1.text.toString(),
            tv_operand2.text.toString(),
            tv_operation.text.toString()
        ).toString()
    }


    private fun diplayWebPageDependingOnResult() {
        val i = Intent(this, WebActivity::class.java)
        val webPage = when (tv_result.text.toString().toInt()) {
            in 0..3 -> "https://kotlinlang.org"
            else -> "https://developer.android.com"
        }
        i.putExtra("webPage", webPage)
        startActivity(i)
    }

    private fun executeOperation(operand1: String, operand2: String, operation: String): Int {
        return when (operation) {
            "+" -> this@MainActivity.service.sum(operand1.toInt(), operand2.toInt())
            "-" -> this@MainActivity.service.substract(operand1.toInt(), operand2.toInt())
            "*" -> this@MainActivity.service.multiply(operand1.toInt(), operand2.toInt())
            "/" -> this@MainActivity.service.divide(operand1.toInt(), operand2.toInt())
            else -> 0
        }
    }

    private fun managePreferences() {
        val prefs = getSharedPreferences("app_setting_receiver", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("activo", true)
        editor.apply()
    }
}
