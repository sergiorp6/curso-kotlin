package es.pue.eventos.model.serviceLayer.manager

import es.pue.eventos.model.serviceLayer.api.IEventosService

interface IServiceManager {
    fun getEventosService(): IEventosService
}