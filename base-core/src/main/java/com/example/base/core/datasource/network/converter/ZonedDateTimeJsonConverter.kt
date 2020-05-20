package com.example.base.core.datasource.network.converter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeJsonConverter {

    @ToJson
    fun toJson(zonedDateTime: ZonedDateTime): String = zonedDateTime.toString()

    @FromJson
    fun fromJson(zonedDateTime: String): ZonedDateTime = ZonedDateTime.parse(zonedDateTime)
}
