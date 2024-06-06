package com.uuranus.schedule.calendar.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun <T> ScheduleCalendarListItem(
    modifier: Modifier,
    data: ScheduleData<T>,
    calendarColors: ScheduleCalendarColors,
    calendarTypography: ScheduleCalendarTypography,
) {
    Row(
        modifier = modifier.background(
            color = if (isSystemInDarkTheme()) {
                lerp(data.color, Color.White, 0.3f)
            } else {
                data.color
            },
            shape = RoundedCornerShape(2.dp)
        )
    ) {
        Text(
            data.title,
            modifier = Modifier.fillMaxSize(),
            style = calendarTypography.scheduleTitle,
            color = calendarColors.dateScheduleTextColor,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
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