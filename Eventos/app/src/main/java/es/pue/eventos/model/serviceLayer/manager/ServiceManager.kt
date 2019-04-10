package es.pue.eventos.model.serviceLayer.manager

import android.content.Context
import android.database.sqlite.SQLiteProgram
import es.pue.eventos.model.persistenceLayer.impl.flatFile.manager.FlatFilePersistenceManager
import es.pue.eventos.model.persistenceLayer.impl.rest.manager.RestPersistenceManager
import es.pue.eventos.model.persistenceLayer.impl.sqlite.manager.SqlitePersistenceManager
import es.pue.eventos.model.serviceLayer.api.IEventosService
import es.pue.eventos.model.serviceLayer.impl.EventosService

class ServiceManager(context: Context) : IServiceManager {

    private val eventoService: IEventosService = EventosService(
        arrayListOf(
            FlatFilePersistenceManager(context),
            SqlitePersistenceManager(context),
            RestPersistenceManager(context)
        )
    )

    override fun getEventosService(): IEventosService {
        return eventoService
    }
}