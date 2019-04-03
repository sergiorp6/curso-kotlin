package es.pue.eventos.model.persistenceLayer.impl.sqlite.dbHelpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import es.pue.eventos.utilitiesLayer.AppUtilities
import java.lang.StringBuilder

class SQLiteDbHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sb = StringBuilder()
        sb.append("CREATE TABLE ")
            .append(AppUtilities.TABLA_EVENTOS)
            .append(" (")
            .append(AppUtilities.TABLA_EVENTOS_ID)
            .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
            .append(AppUtilities.TABLA_EVENTOS_EVENTO)
            .append(" TEXT )")

        db!!.execSQL(sb.toString())

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}