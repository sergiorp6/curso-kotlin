package es.pue.eventos.utilitiesLayer.gsonConverters

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(json!!.asString, LocalDateTime::from)
    }

    override fun serialize(src: LocalDateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format((src)))
    }
}