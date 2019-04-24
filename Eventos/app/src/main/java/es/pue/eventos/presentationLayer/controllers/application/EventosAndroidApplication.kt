package es.pue.eventos.presentationLayer.controllers.application

import android.app.Application
import es.pue.eventos.model.entitiesLayer.Asistencia
import es.pue.eventos.model.entitiesLayer.Evento
import es.pue.eventos.model.serviceLayer.manager.IServiceManager
import es.pue.eventos.model.serviceLayer.manager.ServiceManager

class EventosAndroidApplication : Application() {
    val serviceManager: IServiceManager = ServiceManager(this)
    var asistencia: Asistencia? = null
    var evento: Evento? = null
}
