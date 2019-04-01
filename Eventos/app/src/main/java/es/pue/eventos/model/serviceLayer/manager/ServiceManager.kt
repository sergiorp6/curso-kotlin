package es.pue.eventos.model.serviceLayer.manager

import es.pue.eventos.model.serviceLayer.api.IEventosService
import es.pue.eventos.model.serviceLayer.impl.EventosService

class ServiceManager : IServiceManager {

    lateinit var eventoService: IEventosService

    override fun getEventosService(): IEventosService {
        if (!::eventoService.isInitialized) {
            eventoService = EventosService()
        }
        return eventoService
    }
}