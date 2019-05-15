package es.pue.navegadorweb

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    inner class CalculadoraBinder : Binder() {
        val service: MyService = this@MyService
    }

    private val calculadoraBinder: IBinder = CalculadoraBinder()

    companion object {
        const val SERVICE_DESTROYED = "es.pue.navegadorweb.MyService.SERVICE_DESTROYED"
        const val SERVICE_STARTED = "es.pue.navegadorweb.MyService.SERVICE_STARTED"
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("SERVICE STARTED","Servicio creado")
    }

    override fun onBind(intent: Intent): IBinder = calculadoraBinder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("SERVICE WORKING", "Servicio funcionando")
        sendBroadcast( Intent(SERVICE_STARTED))
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("SERVICE DESTROYED","Servicio destruído")
    }

    fun sumar(x: Int, y: Int): Int = x + y
}
