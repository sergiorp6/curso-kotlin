package es.pue.eventos.model.persistenceLayer.impl.sqlite.daos

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import es.pue.eventos.model.entitiesLayer.Evento
import es.pue.eventos.model.persistenceLayer.api.IEventosDAO
import es.pue.eventos.model.persistenceLayer.impl.sqlite.dbHelpers.SQLiteDbHelper
import es.pue.eventos.utilitiesLayer.AppUtilities
import es.pue.eventos.utilitiesLayer.gsonConverters.LocalDateTimeConverter
import java.time.LocalDateTime
import java.util.*

class EventosDAO(val dbHelper: SQLiteDbHelper) : IEventosDAO {

    private lateinit var db: SQLiteDatabase

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(
            LocalDateTime::class.java,
            LocalDateTimeConverter()
        ).create()

    override fun getEventoByDorsal(dorsal: String): Evento? {
        return getEventos().find { evento ->
            val inscrito = evento.inscritos?.find { inscrito ->
                inscrito.dorsal.equals(dorsal)
            }

            return if (inscrito !== null) {
                evento
            } else {
                null
            }
        }
    }

    override fun getEvento(): Evento? {
        return getEventos().first()
    }

    private fun getEventos(): List<Evento> {
        if (!::db.isInitialized || !db.isOpen) {
            db = dbHelper.readableDatabase
        }

        val cursor = db.query(
            AppUtilities.TABLA_EVENTOS,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val eventos: ArrayList<Evento> = ArrayList()

        var json: String?

        while (cursor.moveToNext()) {
            json = cursor.getString(cursor.getColumnIndex(AppUtilities.TABLA_EVENTOS_EVENTO))

            eventos.add(gson.fromJson(json, Evento::class.java))
        }

        cursor.close()

        return eventos
    }

    override fun saveEvento(evento: Evento) {

        if (!::db.isInitialized || !db.isOpen) {
            db = dbHelper.writableDatabase
        }

        if (evento._id == null) {
            insert(evento)
        } else {
            update(evento)
        }
    }

    private fun insert(evento: Evento) {
        val contentValues = ContentValues()

        evento._id = lastInsertId() + 1
        contentValues.put(AppUtilities.TABLA_EVENTOS_EVENTO, gson.toJson(evento))
        db.insert(AppUtilities.TABLA_EVENTOS, null, contentValues)
    }

    private fun lastInsertId(): Int {
        val cursor = db.query(
            "sqlite_sequence",
            arrayOf("seq"),
            "name=?",
            arrayOf(
                AppUtilities.TABLA_EVENTOS
            ),
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex("seq"))
            cursor.close()

            id
        } else {
            0
        }
    }

    private fun update(evento: Evento) {
        val contentValues = ContentValues()
        contentValues.put(AppUtilities.TABLA_EVENTOS_EVENTO, gson.toJson(evento))

        db.update(
            AppUtilities.TABLA_EVENTOS,
            contentValues,
            AppUtilities.TABLA_EVENTOS_ID + "=?",
            arrayOf(evento._id.toString())
        )
    }
}