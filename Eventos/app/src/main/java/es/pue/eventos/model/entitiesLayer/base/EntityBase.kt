package es.pue.eventos.model.entitiesLayer.base

import java.util.*

abstract class EntityBase(
    uuid: UUID = UUID.randomUUID(),
    dbInsertedDate: Date? = null,
    deletedDate: Date? = null
)