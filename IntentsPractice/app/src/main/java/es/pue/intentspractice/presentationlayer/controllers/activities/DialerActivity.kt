package es.pue.intentspractice.presentationlayer.controllers.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableRow
import es.pue.intentspractice.R
import kotlinx.android.synthetic.main.activity_dialer.*

class DialerActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialer)

        for (i in 0 until dialer_tlButtons.childCount) {
            val fila = dialer_tlButtons.getChildAt(i) as TableRow
            for (j in 0 until fila.childCount) {
                val view = fila.getChildAt(j)
                if (view is Button) {
                    view.setOnClickListener {
                        dialer_etPhoneNUmber.setText(dialer_etPhoneNUmber.text.toString() + view.text.toString())
                    }
                }
            }
        }
    }
}
