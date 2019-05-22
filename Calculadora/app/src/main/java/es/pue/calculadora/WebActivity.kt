package es.pue.calculadora

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    private val htmlReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            visor.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    return false
                }
            }
            visor.loadData(
                intent.getStringExtra("htmlContent"),
                "text/html; charset=utf-8",
                "UTF-8"
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager
            .getInstance(this)
            .registerReceiver(htmlReceiver, IntentFilter(WebRemoteService.HTML_FETCHED))
        startRemoteService()
    }

    private fun startRemoteService() {
        val i = Intent(this, WebRemoteService::class.java)
        i.putExtra("data", intent.getStringExtra("webPage"))
        startService(i)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager
            .getInstance(this)
            .unregisterReceiver(htmlReceiver)
        stopRemoteService()
    }

    private fun stopRemoteService() {
        val i = Intent(this, WebRemoteService::class.java)
        stopService(i)
    }
}
