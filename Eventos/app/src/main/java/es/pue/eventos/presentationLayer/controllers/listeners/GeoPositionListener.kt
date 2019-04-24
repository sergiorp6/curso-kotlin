package es.pue.eventos.presentationLayer.controllers.listeners

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import es.pue.eventos.model.entitiesLayer.Asistencia
import es.pue.eventos.model.entitiesLayer.Posicion
import java.time.LocalDateTime

class GeoPositionListener(private val asistencia: Asistencia?) : LocationListener {
    override fun onLocationChanged(location: Location?) {
        val posicion = Posicion()
        posicion.latitud = location?.latitude
        posicion.longitud = location?.longitude
        posicion.precision = location?.accuracy?.toInt()
        posicion.fecha = LocalDateTime.now()

        asistencia?.recorrido!!.add(posicion)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        return
    }

    override fun onProviderEnabled(provider: String?) {
        return
    }

    override fun onProviderDisabled(provider: String?) {
        return
    }
}