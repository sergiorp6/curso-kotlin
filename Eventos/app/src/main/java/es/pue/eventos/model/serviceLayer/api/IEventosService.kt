package es.pue.eventos.model.serviceLayer.api

import es.pue.eventos.model.entitiesLayer.Evento
import es.pue.eventos.utilitiesLayer.AppUtilities

interface IEventosService {

    fun saveEvento(evento: Evento, persistenceTechnology: AppUtilities.PersistenceTechnologies)

    fun getEventoByDorsal(dorsal: String, persistenceTechnology: AppUtilities.PersistenceTechnologies): Evento?

    fun generateEvento(): Evento
}