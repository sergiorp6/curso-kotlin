package es.pue.calculadora

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager


class WebRemoteService : IntentService("WebRemoteService") {

    companion object {
        const val HTML_FETCHED = "es.pue.calculadora.WebRemoteService.html_fetched"
    }
    override fun onHandleIntent(intent: Intent?) {
        val url = intent?.getStringExtra("data")
        if (url != null) {
            val cadenaHtml = URLData.getData(url)
            val intent = Intent(HTML_FETCHED)
            intent.putExtra("htmlContent", cadenaHtml)
            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
        }
    }
}
