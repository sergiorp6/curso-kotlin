package es.pue.eventos.presentationLayer.controllers.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.IBinder
import es.pue.eventos.presentationLayer.controllers.application.EventosAndroidApplication
import es.pue.eventos.presentationLayer.controllers.listeners.GeoPositionListener

class GeoPositionService : Service() {

    private lateinit var locationManager: LocationManager
    private lateinit var geoPositionListener: GeoPositionListener

    override fun onCreate() {
        super.onCreate()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        geoPositionListener = GeoPositionListener(
            (this.application as EventosAndroidApplication).asistencia
        )
    }

    @SuppressLint("MissingPermission")

    fun iniciarPosicionamiento() {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            20000,
            0f,
            geoPositionListener
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        iniciarPosicionamiento()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates
    }

    override fun onDestroy() {
        locationManager.removeUpdates(geoPositionListener)
        super.onDestroy()
    }
}