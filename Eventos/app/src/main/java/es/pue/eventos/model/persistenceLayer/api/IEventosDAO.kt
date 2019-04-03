package es.pue.eventos.model.persistenceLayer.api

import es.pue.eventos.model.entitiesLayer.Evento

interface IEventosDAO {
    fun getEvento(): Evento?

    fun getEventoByDorsal(dorsal: String): Evento?

    fun saveEvento(evento: Evento)
}