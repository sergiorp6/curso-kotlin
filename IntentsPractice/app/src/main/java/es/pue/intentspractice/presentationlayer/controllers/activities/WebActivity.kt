package es.pue.intentspractice.presentationlayer.controllers.activities

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.pue.intentspractice.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        web_bt_navegar.setOnClickListener {
            val uri = Uri.parse(web_et_web.text.toString())
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        web_bt_buscar.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, web_et_web.text.toString())
            startActivity(intent)
        }
    }
}
