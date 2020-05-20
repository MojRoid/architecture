package com.example.base.core.extension

import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import org.threeten.bp.temporal.ChronoUnit
import java.util.*

fun Long.toZonedDateTimeLocal(): ZonedDateTime = ZonedDateTime
    .ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())

fun Long.toZonedDateTimeUTC(): ZonedDateTime = ZonedDateTime
    .ofInstant(Instant.ofEpochMilli(this), ZoneOffset.UTC)

fun ZonedDateTime.toLocalizedTime(): String = format(
    DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.getDefault())
)

fun ZonedDateTime.toLocalizedDate(): String = format(
    DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.getDefault())
)

fun ZonedDateTime.toLocalizedDateWithDay(): String = format(
    DateTimeFormatter.ofPattern("dd/MM/yyyy - E").withLocale(Locale.getDefault())
)

fun ZonedDateTime.toLocalizedDateTime(): String = format(
    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.getDefault())
)

fun ZonedDateTime.daysBetween(timestamp: ZonedDateTime): Int =
    Period.between(toLocalDate(), timestamp.toLocalDate()).days

fun ZonedDateTime.nanosBetween(timestamp: ZonedDateTime): Long =
    Duration.between(this, timestamp).toNanos()

fun String.toZonedDateTime(): ZonedDateTime = this.let { ZonedDateTime.parse(it) }

fun ZonedDateTime.toStartOfDay(): ZonedDateTime = truncatedTo(ChronoUnit.DAYS)

fun ZonedDateTime.toEndOfDay(): ZonedDateTime = truncatedTo(ChronoUnit.DAYS).plusDays(1)
    .minusSeconds(1)
