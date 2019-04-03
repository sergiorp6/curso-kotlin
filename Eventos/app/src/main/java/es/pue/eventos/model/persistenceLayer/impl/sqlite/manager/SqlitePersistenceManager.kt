package es.pue.eventos.model.persistenceLayer.impl.sqlite.manager

import android.content.Context
import es.pue.eventos.model.persistenceLayer.api.IEventosDAO
import es.pue.eventos.model.persistenceLayer.impl.sqlite.daos.EventosDAO
import es.pue.eventos.model.persistenceLayer.impl.sqlite.dbHelpers.SQLiteDbHelper
import es.pue.eventos.model.persistenceLayer.manager.IPersistenceManager
import es.pue.eventos.utilitiesLayer.AppUtilities

class SqlitePersistenceManager(context: Context) : IPersistenceManager {
    private val eventoDAO: IEventosDAO = EventosDAO(
        SQLiteDbHelper(
            context,
            AppUtilities.EVENTOS_DB,
            null,
            AppUtilities.EVENTOS_DB_VERSION
        )
    )

    override fun getEventosDAO(): IEventosDAO {
        return eventoDAO
    }

}