package es.pue.eventos.model.serviceLayer.impl

import es.pue.eventos.model.entitiesLayer.*
import es.pue.eventos.model.serviceLayer.api.IEventosService
import es.pue.eventos.utilitiesLayer.AppUtilities
import java.time.LocalDateTime

class EventosService : IEventosService {
    override fun saveEvento(evento: Evento, persistenceTechnologies: AppUtilities.PersistenceTechnologies) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEventoByDorsal(
        dorsal: String,
        persistenceTechnologies: AppUtilities.PersistenceTechnologies
    ): Evento? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
}