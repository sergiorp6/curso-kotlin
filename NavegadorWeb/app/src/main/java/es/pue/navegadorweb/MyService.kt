package es.pue.navegadorweb

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    companion object {
        const val SERVICE_DESTROYED = "es.pue.navegadorweb.MyService.SERVICE_DESTROYED"
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("SERVER STARTED", "SERVICIO INICIADO")
        stopSelf()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Intent().apply {
            action = SERVICE_DESTROYED
            sendBroadcast(this)
        }
    }
}
