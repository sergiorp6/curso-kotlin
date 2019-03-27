package es.pue.eventos.model.entitiesLayer

import es.pue.eventos.model.entitiesLayer.base.EntityBase
import java.util.*

class Evento(
    uuid: UUID,
    dbInsertedDate: Date? = null,
    nombre: String? = null,
    descripcion: String? = null,
    numeroPlazas: Int? = null,
    sessiones: List<Corredor>? = null,
    deletedDate: Date? = null
) : EntityBase(uuid, dbInsertedDate, deletedDate) {
}