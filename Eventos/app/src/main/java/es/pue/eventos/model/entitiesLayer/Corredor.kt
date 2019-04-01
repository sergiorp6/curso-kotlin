package es.pue.eventos.model.entitiesLayer

import es.pue.eventos.model.entitiesLayer.base.EntityBase
import java.util.*

class Corredor(
    uuid: UUID = UUID.randomUUID(),
    dbInsertedDate: Date? = null,
    deletedDate: Date? = null,
    var nombre: String? = null,
    var email: String? = null,
    var dorsal: String? = null
) : EntityBase(uuid, dbInsertedDate, deletedDate)