package com.uuranus.schedule.calendar.compose

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.text.DateFormatSymbols

internal fun getYearMonthHeader(format: String, dateInfo: ScheduleDate): String {
    return if (format.contains("%s")) {
        String.format(format, getSystemMonthNames(dateInfo.month), dateInfo.year)
    } else {
        String.format(format, dateInfo.year, dateInfo.month)
    }

}


internal fun getNumberOfDaysInMonth(year: Int, month: Int): Int {
    println("!!!")
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1) // Calendar 클래스는 0부터 시작
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}

internal fun getFirstDayOfMonth(year: Int, month: Int): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1) // Calendar 클래스는 월은 0부터 시작
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    return calendar
}

internal fun getFirstDayOfWeek(calendar: Calendar, isMondayFirst: Boolean): Int {
    return if (isMondayFirst) (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7
    else (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY + 7) % 7
}

internal fun getNextDayOfWeek(year: Int, month: Int, isMondayFirst: Boolean): Int {
    val moreDate = (getNumberOfDaysInMonth(year, month) + getFirstDayOfWeek(
        getFirstDayOfMonth(year, month), isMondayFirst
    )) % 7

    if (moreDate != 0) return 7 - moreDate

    return 0
}


fun getSystemDayOfWeekNames(): List<String> {
    val symbols = DateFormatSymbols.getInstance()
    val shortWeekDays = symbols.shortWeekdays.toList().filter { it.isNotEmpty() }

    val list = shortWeekDays.subList(1, shortWeekDays.size)

    return list.plus(
        shortWeekDays.first()
    )

}


private fun getSystemMonthNames(month: Int): String {
    val symbols = DateFormatSymbols.getInstance()
    return symbols.months[month]
}
