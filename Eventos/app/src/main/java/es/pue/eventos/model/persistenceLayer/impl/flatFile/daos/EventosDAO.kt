package es.pue.eventos.model.persistenceLayer.impl.flatFile.daos

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import es.pue.eventos.model.entitiesLayer.Evento
import es.pue.eventos.model.persistenceLayer.api.IEventosDAO
import es.pue.eventos.utilitiesLayer.gsonConverters.LocalDateTimeConverter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.time.LocalDateTime

class EventosDAO(private val context: Context) : IEventosDAO {
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter())
        .create()

    private val filename = "evento.json"

    override fun getEvento(): Evento? {
        val input = BufferedReader(InputStreamReader(context.openFileInput(filename)))
        val json = input.readLine()
        input.close()

        return gson.fromJson(json, Evento::class.java)
    }

    override fun getEventoByDorsal(dorsal: String): Evento? {

        val evento = getEvento()
        val inscrito = evento?.inscritos?.find {
            it.dorsal == dorsal
        }

        return if (inscrito !== null) {
            evento
        } else {
            null
        }
    }

    override fun saveEvento(evento: Evento) {
        val out = OutputStreamWriter(context.openFileOutput("evento.json", Context.MODE_PRIVATE))

        out.write(gson.toJson(evento))
        out.close()
    }
}
