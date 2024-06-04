//package com.uuranus.schedule.calendar.compose
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.graphics.Color
//import java.time.DayOfWeek
//import java.time.LocalDate
//
//@Composable
//fun rememberScheduleCalendarState(
//    initialDate: LocalDate,
//    isMondayFirst: Boolean,
//    weekDayOfWeek: List<String>,
//    scheduleColors: List<Color>,
//    ) :ScheduleCalendarState{
//    return rememberSaveable(
//        inputs = arrayOf(
//            initialDate,
//            isMondayFirst,
//            weekDayOfWeek,
//            scheduleColors
//        ),
//        saver = CalendarState.Saver,
//    ) {
//        CalendarState(
//            startMonth = startMonth,
//            endMonth = endMonth,
//            firstDayOfWeek = firstDayOfWeek,
//            firstVisibleMonth = firstVisibleMonth,
//            outDateStyle = outDateStyle,
//            visibleItemState = null,
//        )
//    }
//}
//
//
//class ScheduleCalendarState(
//
//)