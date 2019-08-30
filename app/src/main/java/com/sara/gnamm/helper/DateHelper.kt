package com.sara.gnamm.helper

import java.text.SimpleDateFormat
import java.util.*

const val DefaultDateFormat = "dd/MM/yyyy"

/**
 * yearsBetween calculates the number of years between two dates.
 * Default parameter values:
 * end => Date() (today)
 */
fun yearsBetween(start: Date, end: Date = Date()): Int {

    val a = toCalendar(start)
    val b = toCalendar(end)

    var diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR)

    if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
            a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE)) {
        diff--
    }
    return diff
}

fun toCalendar(date: Date): Calendar {
    val cal = Calendar.getInstance(Locale.getDefault())
    cal.time = date
    return cal
}


/**
 * display returns a string representing a date.
 * Default parameter values:
 * format => "dd/MM/yyyy" (23/01/1996)
 */
@JvmOverloads
fun display(date: Date, format: String = DefaultDateFormat): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(date)
}