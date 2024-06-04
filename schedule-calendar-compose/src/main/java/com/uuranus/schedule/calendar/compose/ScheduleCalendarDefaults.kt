package com.uuranus.schedule.calendar.compose

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val White = Color(0xFFFFFFFF)
internal val Black = Color(0xFF040706)
internal val DARK = Color(0xFF040706)
internal val LightGray = Color(0xFFECECEC)
internal val Gray = Color(0xFFD6D6D6)
internal val DarkGray = Color(0xFF969696)

internal val PrimaryGreen = Color(0xFFC1DAB9)

internal val AccentRed = Color(0xFFD52727)
internal val ErrorRed = Color(0xFFD52727)

//calendar colors
internal val Red = Color(0xFFF3A8A8)
internal val Blue = Color(0xFFA8C6E3)
internal val Orange = Color(0xFFFBCFAE)
internal val Yellow = Color(0xFFF9EB9E)
internal val Purple = Color(0xFFC8C7F6)
internal val Green = Color(0xFF9FE9B4)

data class ScheduleCalendarColors(
    val monthHeaderColor: Color = Black,
    val horizontalDividerColor: Color = LightGray,
    val dayOfWeeks: Color = Black,
    val saturdayColor: Color = Black,
    val sundayColor: Color = Black,
    val dateColor: Color = Black,
    val todayAlarmColor: Color = PrimaryGreen,
    val todayAlarmTextColor: Color = White,
    val dateAlarmColor: Color = AccentRed,
    val schedulesColor: List<Color> = listOf(
        Red, Blue, Orange, Yellow, Purple, Green
    ),
)

data class ScheduleCalendarTypography(
    val monthHeader: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    val dayOfWeeks: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    val date: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    val scheduleTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
)

data class ScheduleCalendarFormat(
    val monthHeaderFormat: String = "%04d년 %d월",
    val dayOfWeeks: List<String> = getSystemWeeks(),
)


internal object ScheduleCalendarDefaults {

    val colors = ScheduleCalendarColors()

    val typography = ScheduleCalendarTypography()

    val format = ScheduleCalendarFormat()
}