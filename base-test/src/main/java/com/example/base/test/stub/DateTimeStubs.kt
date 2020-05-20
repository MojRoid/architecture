package com.example.base.test.stub

import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

object DateTimeStubs {

    val ZONED_DATE_TIME: ZonedDateTime = ZonedDateTime.of(
        2020, // Year
        1, // Month
        2, // Day of Month
        3, // Hour
        4, // Minute
        5, // Second
        6, // Nanosecond
        ZoneId.of("UTC") // ZoneId
    )

    const val ZONED_DATE_TIME_STRING = "2020-01-02T03:04:05.000000006Z[UTC]"
}
