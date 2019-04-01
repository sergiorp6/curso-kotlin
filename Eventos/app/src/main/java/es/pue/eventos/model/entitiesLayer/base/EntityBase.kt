package es.pue.eventos.model.entitiesLayer.base

import java.util.*

abstract class EntityBase(
    var uuid: UUID = UUID.randomUUID(),
    var dbInsertedDate: Date? = null,
    var deletedDate: Date? = null
)