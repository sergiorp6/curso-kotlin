package es.pue.eventos.presentationLayer.controllers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.pue.eventos.R
import es.pue.eventos.presentationLayer.controllers.application.EventosAndroidApplication
import es.pue.eventos.utilitiesLayer.AppUtilities

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceManager = (applicationContext as EventosAndroidApplication).serviceManager

        var evento = serviceManager
            .getEventosService()
            .getEventoByDorsal("252", AppUtilities.PersistenceTechnologies.REST)

        if (evento === null) {
            evento = serviceManager.getEventosService().generateEvento()
        }

        serviceManager.getEventosService().saveEvento(evento, AppUtilities.PersistenceTechnologies.REST)
    }
}
