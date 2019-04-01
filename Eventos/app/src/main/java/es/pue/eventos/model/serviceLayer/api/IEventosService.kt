package es.pue.eventos.model.serviceLayer.api

import es.pue.eventos.model.entitiesLayer.Evento

interface IEventosService {

    fun saveEvento(evento: Evento)

    fun getEventoByDorsal(dorsal: String): Evento?

    fun generateEvento(): Evento
}