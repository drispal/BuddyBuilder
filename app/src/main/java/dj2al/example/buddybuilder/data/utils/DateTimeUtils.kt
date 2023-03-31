package dj2al.example.buddybuilder.data.utils

import android.content.Context
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

val currentDateTime: Long = Calendar.getInstance().timeInMillis

fun Context.toDateString(time: Long): String {
    return DateUtils.getRelativeTimeSpanString(
        time,
        Date().time,
        DateUtils.MINUTE_IN_MILLIS,
        DateUtils.FORMAT_ABBREV_RELATIVE
    ).toString()
}


fun Long.toDateTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
    return format.format(date)
}

fun Long.toDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    return format.format(date)
}

fun Long.toDateWithDay(): String {
    val date = Date(this)
    val format = SimpleDateFormat("EEEE dd/MM", Locale.getDefault())
    return format.format(date)
}

fun Long.toTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}

fun Long.getDay(): String {
    val date = Date(this)
    val format = SimpleDateFormat("EEEE", Locale.getDefault())
    return format.format(date)
}