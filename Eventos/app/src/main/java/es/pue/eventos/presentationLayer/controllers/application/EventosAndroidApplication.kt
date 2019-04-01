package es.pue.eventos.presentationLayer.controllers.application

import android.app.Application
import es.pue.eventos.model.serviceLayer.manager.IServiceManager
import es.pue.eventos.model.serviceLayer.manager.ServiceManager

class EventosAndroidApplication : Application() {
    private lateinit var serviceManager: IServiceManager

    fun getServiceManager(): IServiceManager {
        if (!::serviceManager.isInitialized) {
            serviceManager = ServiceManager()
        }

        return serviceManager
    }
}