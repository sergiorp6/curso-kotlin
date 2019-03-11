package es.pue.intentspractice.presentationlayer.controllers.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.pue.intentspractice.R
import kotlinx.android.synthetic.main.activity_gps.*

class GpsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps)

        gps_bt_localizar.setOnClickListener {
            val uri = Uri.parse("geo:0,0?q=${gps_et_search.text}")
            val intent = Intent(Intent.ACTION_VIEW, uri)

            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(this, "No se ha encontrado ninguna app.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
