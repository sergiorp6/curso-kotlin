package es.pue.eventos.model.serviceLayer.impl

import es.pue.eventos.model.entitiesLayer.*
import es.pue.eventos.model.persistenceLayer.impl.flatFile.manager.FlatFilePersistenceManager
import es.pue.eventos.model.persistenceLayer.impl.rest.manager.RestPersistenceManager
import es.pue.eventos.model.persistenceLayer.impl.sqlite.manager.SqlitePersistenceManager
import es.pue.eventos.model.persistenceLayer.manager.IPersistenceManager
import es.pue.eventos.model.serviceLayer.api.IEventosService
import es.pue.eventos.utilitiesLayer.AppUtilities
import java.time.LocalDateTime

class EventosService(private val persistenceManagers: List<IPersistenceManager>) : IEventosService {

    override fun saveEvento(evento: Evento, persistenceTechnology: AppUtilities.PersistenceTechnologies) {
        getPersistenceManagerBy(persistenceTechnology).getEventosDAO().saveEvento(evento)
    }

    private fun getPersistenceManagerBy(
        persistenceTechnology: AppUtilities.PersistenceTechnologies
    ): IPersistenceManager {
        return when (persistenceTechnology) {
            AppUtilities.PersistenceTechnologies.FLAT_FILE -> persistenceManagers.first { pm ->
                pm is FlatFilePersistenceManager
            }
            AppUtilities.PersistenceTechnologies.REST -> persistenceManagers.first { pm ->
                pm is RestPersistenceManager
            }
            AppUtilities.PersistenceTechnologies.SQLITE -> persistenceManagers.first { pm ->
                pm is SqlitePersistenceManager
            }
        }
    }

    override fun generateEvento(): Evento {
        return Evento(
            nombre = "Evento de prueba",
            descripcion = "Descripcion de prueba",
            numeroPlazas = 2,
            inscritos = buildInscritos(),
            sesiones = buildSesiones()
        )
    }

    private fun buildSesiones(): List<Sesion> {
        return arrayListOf(
            Sesion(
                inicio = LocalDateTime.now().minusDays(1),
                fin = LocalDateTime.now().minusDays(1).plusHours(1)
            ),
            Sesion(
                inicio = LocalDateTime.now(),
                fin = LocalDateTime.now().plusMonths(1)
            )
        )
    }

    private fun buildInscritos(): List<Corredor> {
        return arrayListOf(
            Corredor(nombre = "Alba", dorsal = "252", email = "alba@gmail.com"),
            Corredor(nombre = "Pedro", dorsal = "3925", email = "pedro@gmail.com")
        )
    }

    override fun getEventoByDorsal(
        dorsal: String,
        persistenceTechnology: AppUtilities.PersistenceTechnologies
    ): Evento? {
        return getPersistenceManagerBy(persistenceTechnology).getEventosDAO().getEventoByDorsal(dorsal)
    }
}