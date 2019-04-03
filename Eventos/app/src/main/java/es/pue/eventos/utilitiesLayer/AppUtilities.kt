package es.pue.eventos.utilitiesLayer

object AppUtilities {
    enum class PersistenceTechnologies {
        FLAT_FILE, REST, SQLITE
    }

    const val EVENTOS_DB = "eventosDB"
    const val EVENTOS_DB_VERSION = 1
    const val TABLA_EVENTOS = "eventos"
    const val TABLA_EVENTOS_ID = "_id"
    const val TABLA_EVENTOS_EVENTO = "evento"
}