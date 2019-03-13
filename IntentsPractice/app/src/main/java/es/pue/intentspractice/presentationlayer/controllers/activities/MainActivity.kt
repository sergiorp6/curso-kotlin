package es.pue.intentspractice.presentationlayer.controllers.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import es.pue.intentspractice.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_btWeb.setOnClickListener(this)
        main_btGps.setOnClickListener(this)
        main_btDialer.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var intent: Intent? = null

        when (v) {
            main_btWeb    -> intent = Intent(this, WebActivity::class.java)
            main_btGps    -> intent = Intent(this, GpsActivity::class.java)
            main_btDialer -> intent = Intent(this, DialerActivity::class.java)
        }
        startActivity(intent)
    }
}
