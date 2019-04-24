package es.pue.eventos.model.entitiesLayer

import es.pue.eventos.model.entitiesLayer.base.EntityBase
import java.time.LocalDateTime
import java.util.*

class Sesion(
    uuid: UUID = UUID.randomUUID(),
    dbInsertedDate: Date? = null,
    deletedDate: Date? = null,
    var inicio: LocalDateTime? = null,
    var fin: LocalDateTime? = null,
    var asistencias: MutableList<Asistencia>? = ArrayList()
) : EntityBase(uuid, dbInsertedDate, deletedDate)