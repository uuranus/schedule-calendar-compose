package com.uuranus.schedule.calendar.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val White = Color(0xFFFFFFFF)
internal val Black = Color(0xFF040706)
internal val LightGray = Color(0xFFECECEC)
internal val DarkGray = Color(0xFF969696)

internal val PrimaryGreen = Color(0xFFC1DAB9)
internal val AccentRed = Color(0xFFD52727)

internal val DarkPrimaryGreen = Color(0xFF5A7D5C)
internal val DarkAccentRed = Color(0xFFFF6666)

data class ScheduleCalendarColors(
    val monthHeaderColor: Color,
    val horizontalDividerColor: Color,
    val dayOfWeeks: Color,
    val saturdayColor: Color,
    val sundayColor: Color,
    val dateColor: Color,
    val dateScheduleTextColor: Color,
    val todayIndicatorColor: Color,
    val todayIndicatorTextColor: Color,
    val dateAlarmColor: Color,
)

data class ScheduleCalendarTypography(
    val monthHeader: TextStyle,
    val dayOfWeeks: TextStyle,
    val date: TextStyle,
    val scheduleTitle: TextStyle,
)

data class ScheduleCalendarFormat(
    val monthHeaderFormat: String,
    val dayOfWeeks: List<String>,
)

object ScheduleCalendarDefaults {
    @Composable
    fun colors(
        lightColors: ScheduleCalendarColors = defaultLightColors(),
        darkColors: ScheduleCalendarColors = defaultDarkColors(),
    ): ScheduleCalendarColors {
        return if (isSystemInDarkTheme()) {
            darkColors
        } else {
            lightColors
        }
    }

    @Composable
    fun defaultLightColors() = ScheduleCalendarColors(
        monthHeaderColor = Black,
        horizontalDividerColor = LightGray,
        dayOfWeeks = Black,
        saturdayColor = Black,
        sundayColor = Black,
        dateColor = Black,
        dateScheduleTextColor = Black,
        todayIndicatorColor = PrimaryGreen,
        todayIndicatorTextColor = White,
        dateAlarmColor = AccentRed
    )

    @Composable
    fun defaultDarkColors() = ScheduleCalendarColors(
        monthHeaderColor = White,
        horizontalDividerColor = DarkGray,
        dayOfWeeks = White,
        saturdayColor = LightGray,
        sundayColor = LightGray,
        dateColor = White,
        dateScheduleTextColor = Black,
        todayIndicatorColor = DarkPrimaryGreen,
        todayIndicatorTextColor = Black,
        dateAlarmColor = DarkAccentRed
    )

    val typography = ScheduleCalendarTypography(
        monthHeader = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        ),
        dayOfWeeks = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        date = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        scheduleTitle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )
    )

    @Composable
    fun formats() = ScheduleCalendarFormat(
        monthHeaderFormat = "MMMM YYYY",
        dayOfWeeks = getSystemDayOfWeekNames(),
    )
}