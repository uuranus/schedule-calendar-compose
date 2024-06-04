package com.uuranus.schedule.calendar.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.util.Calendar

@Composable
internal fun rememberMonthInfo(currentDate: ScheduleDate): MonthInfo {
    return remember(currentDate) {
        MonthInfo(
            numberOfDays = getNumberOfDaysInMonth(currentDate.year, currentDate.month),
            firstDayOfWeek = getFirstDayOfWeek(
                getFirstDayOfMonth(
                    currentDate.year,
                    currentDate.month
                )
            ),
            nextDayOfWeek = getNextDayOfWeek(currentDate.year, currentDate.month)
        )
    }
}


internal data class MonthInfo(
    val numberOfDays: Int,
    val firstDayOfWeek: Int,
    val nextDayOfWeek: Int,
)


class ScheduleDate private constructor(
    val year: Int,
    val month: Int,
    val date: Int,
) {
    private lateinit var calendar: Calendar

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScheduleDate) return false

        if (year != other.year) return false
        if (month != other.month) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = year
        result = 31 * result + month
        result = 31 * result + date
        return result
    }

    override fun toString(): String {
        return "ScheduleDate(year=$year, month=$month, date=$date)"
    }

    fun addMonth(monthNum: Int): ScheduleDate {
        val newCalendar = calendar.clone() as Calendar
        newCalendar.add(Calendar.MONTH, monthNum)
        return create(newCalendar).apply {
            setCalendar(newCalendar)
        }
    }

    fun setDate(date: Int): ScheduleDate {
        val newCalendar = calendar.clone() as Calendar
        newCalendar.set(Calendar.DATE, date)
        return ScheduleDate(
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH) + 1,
            newCalendar.get(Calendar.DATE)
        ).apply { setCalendar(newCalendar) }
    }

    private fun setCalendar(calendar: Calendar) {
        this.calendar = calendar
    }

    companion object {
        fun create(calendar: Calendar): ScheduleDate {
            return ScheduleDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DATE)
            ).apply { setCalendar(calendar) }
        }

        fun create(year: Int, month: Int, date: Int): ScheduleDate {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month - 1)
            calendar.set(Calendar.DATE, date)

            return ScheduleDate(year, month, date).apply {
                setCalendar(calendar)
            }
        }

    }

}

