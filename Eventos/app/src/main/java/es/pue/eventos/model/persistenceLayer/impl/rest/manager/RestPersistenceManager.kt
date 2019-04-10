package es.pue.eventos.model.persistenceLayer.impl.rest.manager

import android.content.Context
import es.pue.eventos.model.persistenceLayer.api.IEventosDAO
import es.pue.eventos.model.persistenceLayer.impl.rest.daos.EventosDAO
import es.pue.eventos.model.persistenceLayer.manager.IPersistenceManager

class RestPersistenceManager(private val context: Context): IPersistenceManager {
    private lateinit var eventoDAO: IEventosDAO

    override fun getEventosDAO(): IEventosDAO {
        if (!::eventoDAO.isInitialized) {
            eventoDAO = EventosDAO(context)
        }
        return eventoDAO
    }
}