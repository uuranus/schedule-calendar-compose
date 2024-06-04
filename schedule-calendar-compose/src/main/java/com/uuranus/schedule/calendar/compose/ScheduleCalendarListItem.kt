package com.uuranus.schedule.calendar.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.reflect.typeOf

@Composable
internal fun <T> ScheduleCalendarListItem(
    modifier: Modifier,
    data: ScheduleData<T>,
    calendarTypography: ScheduleCalendarTypography,
) {
    Row(
        modifier = modifier.background(
            color = data.color,
            shape = RoundedCornerShape(2.dp)
        )
    ) {
        Text(
            data.title,
            modifier = Modifier.fillMaxSize(),
            style = calendarTypography.scheduleTitle,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ScheduleCalendarMoreListItem(
    modifier: Modifier,
    moreCount: Int,
    calendarTypography: ScheduleCalendarTypography,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            "+${moreCount}",
            modifier = Modifier.fillMaxSize(),
            style = calendarTypography.scheduleTitle,
        )
    }
}