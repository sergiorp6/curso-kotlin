package es.pue.eventos.model.persistenceLayer.impl.rest.manager

import android.content.Context
import es.pue.eventos.model.persistenceLayer.api.IEventosDAO
import es.pue.eventos.model.persistenceLayer.manager.IPersistenceManager

class RestPersistenceManager(private val context: Context): IPersistenceManager {
    override fun getEventosDAO(): IEventosDAO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}