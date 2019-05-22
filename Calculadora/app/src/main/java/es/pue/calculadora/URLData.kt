package es.pue.calculadora

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object URLData {
    @Throws(IOException::class)
    fun getData(url: String): String? {
        val url = URL(url)

        val conn = url.openConnection() as HttpsURLConnection
        conn.requestMethod = "GET"
        val sb: StringBuilder? = StringBuilder()
        try {
            val inputStream = conn.inputStream

            if (conn.responseCode != HttpURLConnection.HTTP_OK) {
                throw IOException("Error en la conexion")
            }

            if (inputStream != null) {

                val reader = BufferedReader(InputStreamReader(inputStream))
                var line = reader.readLine()
                while (line != null) {
                    sb?.append(line)?.append('\n')
                    line = reader.readLine()
                }

                reader.close()
            }
        } catch (ioex: IOException) {

        }
        return sb?.toString()
    }
}