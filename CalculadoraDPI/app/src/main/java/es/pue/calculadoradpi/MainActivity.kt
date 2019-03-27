package es.pue.calculadoradpi

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow
import kotlin.math.sqrt
import android.util.DisplayMetrics
import android.view.Display
import android.view.Surface
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    private var display: Display? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

        if (display?.rotation == Surface.ROTATION_0 || display?.rotation == Surface.ROTATION_180) {
            verticalMode()
        } else {
            horizontalMode()
        }
    }

    private fun verticalMode() {
        main_bt_calcular!!.setOnClickListener {
            calculateManually()
        }
    }

    private fun horizontalMode() {
        calculateAutomatically()
    }

    private fun calculateManually() {
        val dpis = calculateDpis(
            main_et_rh?.text.toString().toDouble(),
            main_et_rv?.text.toString().toDouble(),
            main_et_inches?.text.toString().toDouble()
        )
        main_tv_dpi_result?.text = String.format("%.2f", dpis)
    }

    private fun calculateDpis(width: Double, height: Double, inches: Double): Double {
        return sqrt(height.pow(2) + width.pow(2)) / inches
    }

    private fun calculateAutomatically() {
        val displayMetrics = DisplayMetrics()

        display?.getMetrics(displayMetrics)

        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        val dpi = displayMetrics.densityDpi
        val inches = Math.hypot(
            displayMetrics.heightPixels.toDouble(),
            displayMetrics.widthPixels.toDouble()
        ) / displayMetrics.densityDpi

        main_land_tv_rv_result?.text = height.toString()
        main_land_tv_rh_result?.text = width.toString()
        main_land_tv_inches_result?.text = String.format("%.2f", inches)
        main_land_tv_dpi_result?.text = dpi.toString()
    }
}
