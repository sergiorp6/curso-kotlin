package es.pue.eventos.presentationLayer.controllers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.pue.eventos.R
import es.pue.eventos.model.serviceLayer.manager.IServiceManager
import es.pue.eventos.presentationLayer.controllers.application.EventosAndroidApplication
import es.pue.eventos.utilitiesLayer.AppUtilities

class MainActivity : AppCompatActivity() {

    private val serviceManager: IServiceManager =
        (applicationContext as EventosAndroidApplication)
            .getServiceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val evento = serviceManager
            .getEventosService()
            .generateEvento()

        serviceManager.getEventosService().saveEvento(
            evento,
            AppUtilities.PersistenceTechnologies.FLAT_FILE
        )
    }
}
