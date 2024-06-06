package com.uuranus.schedule.calendar.compose

import androidx.compose.ui.graphics.Color

data class ScheduleInfo<T>(
    val isCheckNeeded: Boolean,
    val schedules: List<ScheduleData<T>>,
)

data class ScheduleData<T>(
    val title: String,
    val color: Color,
    val detail: T,
)

