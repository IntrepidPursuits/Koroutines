package io.intrepid.koroutines.api.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

class UUIDMoshiAdapter {
    @ToJson
    internal fun toJson(id: UUID): String {
        return id.toString()
    }

    @FromJson
    internal fun fromJson(id: String): UUID {
        return UUID.fromString(id)
    }
}
