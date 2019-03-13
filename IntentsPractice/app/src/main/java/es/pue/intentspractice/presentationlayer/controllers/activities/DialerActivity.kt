package es.pue.intentspractice.presentationlayer.controllers.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.Button
import android.widget.TableRow
import android.widget.Toast
import es.pue.intentspractice.R
import kotlinx.android.synthetic.main.activity_dialer.*

class DialerActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialer)

        for (i in 0 until dialer_tlButtons.childCount) {
            val fila = dialer_tlButtons.getChildAt(i) as TableRow
            for (j in 0 until fila.childCount) {
                val view = fila.getChildAt(j)
                view.setOnClickListener(this)
            }
        }
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            typePhoneNumber(v)
        }
        if (v === dialer_btCall) {
            if (callPermissionsGranted()) {
                requestCallPermissions()
            } else {
                call()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun typePhoneNumber(v: Button) {
        dialer_etPhoneNUmber.setText("${dialer_etPhoneNUmber.text}${v.text}")
    }

    private fun callPermissionsGranted(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        ) != PackageManager.PERMISSION_GRANTED
    }

    private fun requestCallPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
    }

    private fun call() {
        val uri = Uri.parse("tel:${dialer_etPhoneNUmber.text}")
        val intent = Intent(Intent.ACTION_CALL, uri)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call()
                } else {
                    Toast.makeText(this, "No tienes permiso para call", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
