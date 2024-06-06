package com.uuranus.schedule.calendar.compose

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

internal fun getYearMonthHeader(format: String, dateInfo: ScheduleDate): String {
    return String.format(format, dateInfo.year, dateInfo.month)
}

internal fun getNumberOfDaysInMonth(year: Int, month: Int): Int {
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

internal fun getSystemWeeks(): List<String> =
    SimpleDateFormat("EEEEE", Locale.getDefault()).run {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val week = mutableListOf<String>()
        repeat(7) {
            week.add(format(cal.time))
            cal.add(Calendar.DAY_OF_WEEK, 1)
        }
        week
    }