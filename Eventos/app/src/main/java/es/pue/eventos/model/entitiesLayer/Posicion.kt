package es.pue.eventos.model.entitiesLayer

import es.pue.eventos.model.entitiesLayer.base.EntityBase
import java.time.LocalDateTime
import java.util.*

class Posicion(
    uuid: UUID = UUID.randomUUID(),
    dbInsertedDate: Date? = null,
    deletedDate: Date? = null,
    var latitud: Double? = null,
    var longitud: Double? = null,
    var precision: Int? = null,
    var fecha: LocalDateTime? = null
) : EntityBase(uuid, dbInsertedDate, deletedDate)