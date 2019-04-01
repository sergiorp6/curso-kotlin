package es.pue.eventos.model.entitiesLayer

import es.pue.eventos.model.entitiesLayer.base.EntityBase
import java.util.*

class Evento(
    uuid: UUID = UUID.randomUUID(),
    dbInsertedDate: Date? = null,
    deletedDate: Date? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var numeroPlazas: Int? = null,
    var inscritos: List<Corredor>? = null,
    var sesiones: List<Sesion>? = null
) : EntityBase(uuid, dbInsertedDate, deletedDate)