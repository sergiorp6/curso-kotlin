package es.pue.eventos.presentationLayer.controllers.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import es.pue.eventos.R
import es.pue.eventos.model.entitiesLayer.Asistencia
import es.pue.eventos.presentationLayer.controllers.application.EventosAndroidApplication
import es.pue.eventos.presentationLayer.controllers.services.GeoPositionService
import es.pue.eventos.utilitiesLayer.AppUtilities
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private lateinit var application: EventosAndroidApplication
    private lateinit var intentService: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!::application.isInitialized) {
            application = applicationContext as EventosAndroidApplication
        }

        if (!::intentService.isInitialized) {
            intentService = Intent(this, GeoPositionService::class.java)
        }

        setContentView(R.layout.activity_main)

        val evento = application.serviceManager.getEventosService().generateEvento()

        application.serviceManager.getEventosService()
            .saveEvento(evento, AppUtilities.PersistenceTechnologies.FLAT_FILE)

        main_btAsistencia.setOnClickListener {
            if (noPermissionToLocation()) {
                requestLocationPermission()
            } else {
                iniciarAsistencia(
                    main_etDorsal.text.toString()
                )
            }
        }

        main_btParar.setOnClickListener {
            pararAsistencia(main_etDorsal.text.toString())
        }
    }

    private fun noPermissionToLocation(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
    }

    private fun iniciarAsistencia(dorsal: String) {
        val evento = application.serviceManager.getEventosService()
            .getEventoByDorsal(dorsal, AppUtilities.PersistenceTechnologies.FLAT_FILE)

        val sesion = evento?.sesiones?.find {
            it.inicio!!.isBefore(LocalDateTime.now())
                    && it.fin!!.isAfter(LocalDateTime.now())
        }
        val corredor = evento?.inscritos?.find { it.dorsal == dorsal }

        application.asistencia = Asistencia(corredor = corredor, inicio = LocalDateTime.now())

        sesion?.asistencias?.add(application.asistencia!!)

        startService(intentService)
    }

    private fun pararAsistencia(dorsal: String) {
        stopService(intentService)
        val evento = application.serviceManager.getEventosService()
            .getEventoByDorsal(dorsal, AppUtilities.PersistenceTechnologies.FLAT_FILE)

        application.serviceManager.getEventosService()
            .saveEvento(evento!!, AppUtilities.PersistenceTechnologies.FLAT_FILE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.any { it == PackageManager.PERMISSION_GRANTED }) {
                    iniciarAsistencia(main_etDorsal.text.toString())
                } else {
                    Toast.makeText(this, "No tienes permiso para realizar esta acción.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
