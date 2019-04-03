package es.pue.eventos.model.persistenceLayer.manager

import es.pue.eventos.model.persistenceLayer.api.IEventosDAO

interface IPersistenceManager {
    fun getEventosDAO(): IEventosDAO
}