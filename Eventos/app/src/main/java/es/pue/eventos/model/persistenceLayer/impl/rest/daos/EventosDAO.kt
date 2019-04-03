package es.pue.eventos.model.persistenceLayer.impl.rest.daos

import es.pue.eventos.model.entitiesLayer.Evento
import es.pue.eventos.model.persistenceLayer.api.IEventosDAO

class EventosDAO: IEventosDAO{
    override fun getEventoByDorsal(dorsal: String): Evento? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEvento(): Evento? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveEvento(evento: Evento) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}