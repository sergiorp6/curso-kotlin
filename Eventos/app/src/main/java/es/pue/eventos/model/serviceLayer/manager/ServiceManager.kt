package es.pue.eventos.model.serviceLayer.manager

import android.content.Context
import es.pue.eventos.model.persistenceLayer.impl.flatFile.manager.FlatFilePersistenceManager
import es.pue.eventos.model.serviceLayer.api.IEventosService
import es.pue.eventos.model.serviceLayer.impl.EventosService

class ServiceManager(context: Context) : IServiceManager {

    private val eventoService: IEventosService = EventosService(
        arrayListOf(
            FlatFilePersistenceManager(context)
        )
    )

    override fun getEventosService(): IEventosService {
        return eventoService
    }
}