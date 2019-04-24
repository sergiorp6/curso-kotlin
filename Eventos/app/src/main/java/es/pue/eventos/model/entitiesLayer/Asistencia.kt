package es.pue.eventos.model.entitiesLayer

import es.pue.eventos.model.entitiesLayer.base.EntityBase
import java.time.LocalDateTime
import java.util.*

class Asistencia(
    uuid: UUID = UUID.randomUUID(),
    dbInsertedDate: Date? = null,
    deletedDate: Date? = null,
    var corredor: Corredor? = null,
    var inicio: LocalDateTime? = null,
    var fin: LocalDateTime? = null,
    var recorrido: MutableList<Posicion>? = ArrayList()
) : EntityBase(uuid, dbInsertedDate, deletedDate)