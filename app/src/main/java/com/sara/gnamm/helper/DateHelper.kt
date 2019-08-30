package com.sara.gnamm.helper

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
    companion object {

        const val DefaultDateFormat = "dd/MM/yyyy"

        /**
         * yearsBetween calculates the number of years between two dates.
         */
        //Default parameter values
        @JvmStatic
        fun yearsBetween(start: Date, end: Date): Int {

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
         */
        @JvmStatic
        fun display(date: Date, format: String): String {
            return SimpleDateFormat(format, Locale.getDefault()).format(date)
        }
    }
}