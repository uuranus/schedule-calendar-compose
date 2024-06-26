package com.uuranus.schedule.calendar.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.uuranus.schedule.calendar.compose.ui.theme.Blue
import com.uuranus.schedule.calendar.compose.ui.theme.DarkBlue
import com.uuranus.schedule.calendar.compose.ui.theme.DarkRed
import com.uuranus.schedule.calendar.compose.ui.theme.Green
import com.uuranus.schedule.calendar.compose.ui.theme.Orange
import com.uuranus.schedule.calendar.compose.ui.theme.Purple
import com.uuranus.schedule.calendar.compose.ui.theme.Red
import com.uuranus.schedule.calendar.compose.ui.theme.SchedulecalendarcomposeTheme
import com.uuranus.schedule.calendar.compose.ui.theme.Yellow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchedulecalendarcomposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calendar()
                }
            }
        }
    }
}

@Composable
fun Calendar() {
    val date1 = ScheduleDate.create(2024, 6, 1)
    val date2 = ScheduleDate.create(2024, 6, 12)
    val date3 = ScheduleDate.create(2024, 6, 3)
    val date4 = ScheduleDate.create(2024, 6, 24)
    val date5 = ScheduleDate.create(2024, 6, 14)

    // Create ScheduleInfo objects
    val info1 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                "schedule1",
                Purple,
                "Schedule Info 1",
            )
        )
    )
    val info2 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                "schedule2",
                Blue,
                "Schedule Info 2",
            )
        )
    )
    val info3 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                "schedule3",
                Red,
                "Schedule Info 3",
            )
        )
    )
    val info4 = ScheduleInfo(
        true, listOf(
            ScheduleData(
                "schedule4",
                Orange,
                "Schedule Info 4",
            )
        )
    )
    val info5 = ScheduleInfo(
        true, listOf(
            ScheduleData(
                "schedule5",
                Yellow,
                "Schedule Info 5",
            ),
            ScheduleData(
                "schedule6",
                Orange,
                "Schedule Info 6",
            ),
            ScheduleData(
                "schedule7",
                Green,
                "Schedule Info 7",
            ),
            ScheduleData(
                "schedule8",
                Blue,
                "Schedule Info 8",
            ),
            ScheduleData(
                "schedule9",
                Purple,
                "Schedule Info 9",
            )
        )
    )

    // Create a map with the dummy dates and schedule info
    val schedules = mapOf(
        date1 to info1,
        date2 to info2,
        date3 to info3,
        date4 to info4,
        date5 to info5
    )

    ScheduleCalendar(
        schedules = schedules,
        calendarFormat = ScheduleCalendarDefaults.formats().copy(
            monthHeaderFormat = "YYYY년 MMMM"
        )
    )
}
