package es.pue.eventos.model.persistenceLayer.impl.rest.daos

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import es.pue.eventos.model.entitiesLayer.Evento
import es.pue.eventos.model.persistenceLayer.api.IEventosDAO
import es.pue.eventos.utilitiesLayer.gsonConverters.LocalDateTimeConverter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime

class EventosDAO(private val context: Context) : IEventosDAO {

    override fun getEventoByDorsal(dorsal: String): Evento? {
        val task = GetEventoByDorsal()
        task.execute(dorsal)
        return task.get()
    }

    override fun getEvento(): Evento? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveEvento(evento: Evento) {
        SaveEvento().execute(evento)
    }
}

class SaveEvento : AsyncTask<Evento, Evento, Boolean>() {

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(
            LocalDateTime::class.java,
            LocalDateTimeConverter()
        ).create()

    override fun doInBackground(vararg params: Evento?): Boolean? {

        val connection = URL("https://test.local/Evento/Save")
            .openConnection() as HttpURLConnection

        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        connection.connect()

        val wr = OutputStreamWriter(connection.outputStream)
        wr.write(gson.toJson(params[0]))
        wr.flush()

        when (connection.responseCode) {
            201 -> {
                return true
            }
        }
        return false
    }
}

class GetEventoByDorsal : AsyncTask<String, Intent, Evento>() {

    private var response: Evento? = null
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(
            LocalDateTime::class.java,
            LocalDateTimeConverter()
        ).create()

    override fun doInBackground(vararg params: String?): Evento? {
        val connection = URL("https://test.local/Evento/Dorsal/${params[0]}")
            .openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.connect()

        when (connection.responseCode) {
            200 -> {
                val br = BufferedReader(InputStreamReader(connection.inputStream))

                response = gson.fromJson(br.readLine(), Evento::class.java)

                br.close()

                return response
            }
        }


        return null
    }
}