@file:JvmName("DateHelper")

package com.sara.gnamm.extensions

import java.text.SimpleDateFormat
import java.util.*

const val DefaultDateFormat = "dd/MM/yyyy"
const val ISODateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"

val Date.ISORepresentation: String
    get() = this.display(ISODateFormat)
/**
 * yearsBetween calculates the number of years between two dates.
 * Default parameter values:
 * end => Date() (today)
 */
fun Date.yearsBetween(end: Date = Date()): Int {
    val a = toCalendar(this)
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
fun Date.display(format: String = DefaultDateFormat): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}