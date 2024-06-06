package com.uuranus.schedule.calendar.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Calendar

internal val today = ScheduleDate.create(Calendar.getInstance())

@Composable
fun <T> ScheduleCalendar(
    modifier: Modifier = Modifier,
    initialDate: ScheduleDate = today,
    isMondayFirst: Boolean = true,
    schedules: Map<ScheduleDate, ScheduleInfo<T>>,
    onDayClick: (ScheduleDate) -> Unit = {},
    onPageChanged: (ScheduleDate) -> Unit = {},
    calendarTypography: ScheduleCalendarTypography = ScheduleCalendarDefaults.typography,
    calendarColors: ScheduleCalendarColors = ScheduleCalendarDefaults.colors(),
    calendarFormat: ScheduleCalendarFormat = ScheduleCalendarDefaults.format,
) {

    var currentDate by rememberCurrentDate(initialDate)

    val monthInfo = rememberMonthInfo(currentDate, isMondayFirst)

    val pagerState: PagerState =
        rememberPagerState(pageCount = { Int.MAX_VALUE }, initialPage = Int.MAX_VALUE / 2)

    LaunchedEffect(pagerState.currentPage) {

        if (pagerState.lastScrolledForward) {
            currentDate = currentDate.addMonth(1)
            if (pagerState.currentPage == pagerState.pageCount - 1) {
                pagerState.animateScrollToPage(1)
            }
        } else if (pagerState.lastScrolledBackward) {
            currentDate = currentDate.addMonth(-1)
            if (pagerState.currentPage == 0) {
                pagerState.animateScrollToPage(pagerState.pageCount - 2)
            }
        }

        onPageChanged(currentDate.setDate(1))
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxSize(),
    ) { _ ->

        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            ScheduleCalendarHeader(
                currentDate,
                calendarFormat = calendarFormat,
                calendarTypography = calendarTypography
            )
            ScheduleCalendarMonth(
                isMondayFirst,
                currentDate,
                monthInfo,
                schedules,
                onDayClick,
                calendarTypography,
                calendarColors,
                calendarFormat
            )
        }
    }

}

@Composable
internal fun ScheduleCalendarHeader(
    currentDate: ScheduleDate,
    calendarFormat: ScheduleCalendarFormat,
    calendarTypography: ScheduleCalendarTypography,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = getYearMonthHeader(calendarFormat.monthHeaderFormat, currentDate),
            style = calendarTypography.monthHeader
        )
    }
}

@Composable
internal fun <T> ScheduleCalendarMonth(
    isMondayFirst: Boolean,
    currentDate: ScheduleDate,
    monthInfo: MonthInfo,
    schedules: Map<ScheduleDate, ScheduleInfo<T>>,
    onDayClick: (ScheduleDate) -> Unit,
    calendarTypography: ScheduleCalendarTypography,
    calendarColors: ScheduleCalendarColors,
    calendarFormat: ScheduleCalendarFormat,
) {
    val satIndex = if (isMondayFirst) 5 else 6
    val sunIndex = if (isMondayFirst) 6 else 0

    LazyVerticalGrid(columns = GridCells.Fixed(7)) {
        items(7) {
            ScheduleCalendarWeekDay(
                isMondayFirst = isMondayFirst,
                weekNum = it,
                weekDayColor = when (it) {
                    satIndex -> {
                        calendarColors.saturdayColor
                    }

                    sunIndex -> {
                        calendarColors.sundayColor
                    }

                    else -> {
                        calendarColors.dayOfWeeks
                    }
                },
                calendarTypography = calendarTypography,
                calendarFormat = calendarFormat
            )
        }
        items(monthInfo.firstDayOfWeek) { preIndex ->
            val date = currentDate.setDate(1 + preIndex - monthInfo.firstDayOfWeek)
            EmptyScheduleCalendarDate(
                modifier = Modifier,
                date = date,
                dateColor = when (preIndex % 7) {
                    satIndex -> {
                        calendarColors.saturdayColor
                    }

                    sunIndex -> {
                        calendarColors.sundayColor
                    }

                    else -> {
                        calendarColors.dayOfWeeks
                    }
                },
                calendarTypography = calendarTypography,
            )
        }
        items(monthInfo.numberOfDays) { dayIndex ->
            val date = currentDate.setDate(dayIndex + 1)
            val currentDateSchedules = schedules[date] ?: ScheduleInfo(false, emptyList())
            ScheduleCalendarDate(
                modifier = Modifier,
                date = date,
                isCheckNeeded = currentDateSchedules.isCheckNeeded,
                isToday = date == today && today.date == dayIndex + 1,
                scheduleInfo = currentDateSchedules,
                onDayClick = onDayClick,
                calendarTypography = calendarTypography,
                calendarColors = calendarColors,
                dateColor = when ((monthInfo.firstDayOfWeek + dayIndex) % 7) {
                    satIndex -> {
                        calendarColors.saturdayColor
                    }

                    sunIndex -> {
                        calendarColors.sundayColor
                    }

                    else -> {
                        calendarColors.dayOfWeeks
                    }
                }
            )
        }
        items(monthInfo.nextDayOfWeek) { nextIndex ->
            val date = currentDate.setDate(nextIndex + 1)
            EmptyScheduleCalendarDate(
                modifier = Modifier,
                date = date,
                dateColor = when ((monthInfo.firstDayOfWeek + monthInfo.numberOfDays + nextIndex) % 7) {
                    satIndex -> {
                        calendarColors.saturdayColor
                    }

                    sunIndex -> {
                        calendarColors.sundayColor
                    }

                    else -> {
                        calendarColors.dayOfWeeks
                    }
                },
                calendarTypography = calendarTypography,
            )
        }
    }
}

@Composable
internal fun ScheduleCalendarWeekDay(
    isMondayFirst: Boolean,
    weekNum: Int,
    weekDayColor: Color,
    calendarTypography: ScheduleCalendarTypography,
    calendarFormat: ScheduleCalendarFormat,
) {
    val weekDays = if (isMondayFirst) calendarFormat.dayOfWeeks
    else {
        listOf(calendarFormat.dayOfWeeks.last()).plus(
            calendarFormat.dayOfWeeks.subList(0, calendarFormat.dayOfWeeks.size - 1)
        )
    }

    val dividerColor =
        ScheduleCalendarDefaults.colors().horizontalDividerColor

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {

                val strokeWidth = 0.7f * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    dividerColor,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            weekDays[weekNum],
            style = calendarTypography.dayOfWeeks,
            color = weekDayColor
        )
    }
}

@Composable
internal fun <T> ScheduleCalendarDate(
    modifier: Modifier,
    date: ScheduleDate,
    isCheckNeeded: Boolean,
    isToday: Boolean,
    scheduleInfo: ScheduleInfo<T>,
    onDayClick: (ScheduleDate) -> Unit,
    calendarTypography: ScheduleCalendarTypography,
    calendarColors: ScheduleCalendarColors,
    dateColor: Color,
) {

    val dividerColor =
        ScheduleCalendarDefaults.colors().horizontalDividerColor

    Column(
        modifier = modifier
            .height(100.dp)
            .drawBehind {

                val strokeWidth = 0.7f * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    dividerColor,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
            .clickable {
                onDayClick(date)
            }
            .padding(3.dp),
    ) {
        DateHeader(
            date, isCheckNeeded, isToday,
            calendarTypography = calendarTypography,
            calendarColors = calendarColors,
            dateColor = dateColor,
        )
        DateContent(
            Modifier.fillMaxHeight(), scheduleInfo.schedules,
            calendarColors = calendarColors,
            calendarTypography = calendarTypography
        )
    }
}

@Composable
internal fun EmptyScheduleCalendarDate(
    modifier: Modifier,
    date: ScheduleDate,
    dateColor: Color,
    calendarTypography: ScheduleCalendarTypography,
) {
    val dividerColor =
        ScheduleCalendarDefaults.colors().horizontalDividerColor
    Column(
        modifier = modifier
            .height(100.dp)
            .drawBehind {

                val strokeWidth = 0.7f * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    dividerColor,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
            .padding(3.dp),
    ) {
        NonCurrentMonthDateHeader(
            date = date,
            dateColor = dateColor,
            calendarTypography = calendarTypography,
        )
    }
}


@Composable
internal fun DateHeader(
    date: ScheduleDate,
    isCheckNeeded: Boolean,
    isToday: Boolean,
    calendarTypography: ScheduleCalendarTypography,
    calendarColors: ScheduleCalendarColors,
    dateColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (isCheckNeeded) {
            Box(
                modifier = Modifier
                    .background(
                        color = calendarColors.dateAlarmColor,
                        shape = CircleShape
                    )
                    .size(8.dp)
                    .align(Alignment.TopEnd)
            )
        }

        if (isToday) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(
                        color = calendarColors.todayIndicatorColor,
                        shape = CircleShape
                    )
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.date.toString(),
                    style = calendarTypography.date,
                    color = calendarColors.todayIndicatorTextColor
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.date.toString(),
                    style = calendarTypography.date,
                    color = dateColor
                )
            }
        }
    }
}

@Composable
internal fun NonCurrentMonthDateHeader(
    date: ScheduleDate,
    dateColor: Color,
    calendarTypography: ScheduleCalendarTypography,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = date.date.toString(),
                style = calendarTypography.date,
                color = dateColor.copy(alpha = 0.4f)
            )
        }
    }
}

@Composable
internal fun <T> DateContent(
    modifier: Modifier,
    schedules: List<ScheduleData<T>>,
    calendarColors: ScheduleCalendarColors,
    calendarTypography: ScheduleCalendarTypography,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(schedules.size) { index ->
            if (index < 4) {
                ScheduleCalendarListItem(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 1.dp),
                    data = schedules[index],
                    calendarColors = calendarColors,
                    calendarTypography = calendarTypography,
                )
            } else if (index == 4) {
                ScheduleCalendarMoreListItem(
                    Modifier.fillMaxWidth(), schedules.size - index,
                    calendarTypography = calendarTypography,
                )
            }
        }
    }
}
