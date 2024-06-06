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
import androidx.compose.ui.tooling.preview.Preview
import com.uuranus.schedule.calendar.compose.ui.theme.Blue
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
                // A surface container using the 'background' color from the theme
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
    val date5 = ScheduleDate.create(2024, 6, 15)

    // Create ScheduleInfo objects
    val info1 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                "Schedule Info 1",
                Purple,
                "Schedule Info 1",
            )
        )
    )
    val info2 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                "Schedule Info 2",
                Blue,
                "Schedule Info 2",
            )
        )
    )
    val info3 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                "Schedule Info 3",
                Red,
                "Schedule Info 3",
            )
        )
    )
    val info4 = ScheduleInfo(
        true, listOf(
            ScheduleData(
                "Schedule Info 4",
                Orange,
                "Schedule Info 4",
            )
        )
    )
    val info5 = ScheduleInfo(
        true, listOf(
            ScheduleData(
                "Schedule Info 5",
                Yellow,
                "Schedule Info 5",
            ),
            ScheduleData(
                "Schedule Info 5",
                Orange,
                "Schedule Info 5",
            ),
            ScheduleData(
                "Schedule Info 5",
                Green,
                "Schedule Info 5",
            ),
            ScheduleData(
                "Schedule Info 5",
                Blue,
                "Schedule Info 5",
            ),
            ScheduleData(
                "Schedule Info 5",
                Purple,
                "Schedule Info 5",
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
        modifier = Modifier,
        isMondayFirst = false,
        initialDate = ScheduleDate.create(2024, 6, 2),
        schedules = schedules,
        onDayClick = {

        },
        onPageChanged = {

        },
        calendarFormat = ScheduleCalendarDefaults.format.copy(
            dayOfWeeks = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        )

    )
}
