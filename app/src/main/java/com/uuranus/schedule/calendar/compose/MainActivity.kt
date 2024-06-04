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
import com.uuranus.schedule.calendar.compose.ui.theme.SchedulecalendarcomposeTheme

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
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val date1 = ScheduleDate.create(2024, 6, 1)
    val date2 = ScheduleDate.create(2024, 6, 12)
    val date3 = ScheduleDate.create(2024, 6, 3)
    val date4 = ScheduleDate.create(2024, 6, 24)
    val date5 = ScheduleDate.create(2024, 6, 15)

    // Create ScheduleInfo objects
    val info1 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                1, "Schedule Info 1",
                Color.Yellow,
                "Schedule Info 1",
            )
        )
    )
    val info2 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                2, "Schedule Info 2",
                Color.Yellow,
                "Schedule Info 2",
            )
        )
    )
    val info3 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                3, "Schedule Info 3",
                Color.Yellow,
                "Schedule Info 3",
            )
        )
    )
    val info4 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                4, "Schedule Info 4",
                Color.Yellow,
                "Schedule Info 4",
            )
        )
    )
    val info5 = ScheduleInfo(
        false, listOf(
            ScheduleData(
                5, "Schedule Info 5",
                Color.Yellow,
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
        isMondayFirst = true,
        initialDate = ScheduleDate.create(2024, 6, 2),
        schedules = schedules,
        onDayClick = {},
        onPageChanged = {

        },
        calendarColors = ScheduleCalendarColors(
            saturdayColor = Color.Blue,
            sundayColor = Color.Red,
        ),
        calendarFormat = ScheduleCalendarFormat().copy(
            monthHeaderFormat = "%04d년 %2d월",
            dayOfWeeks = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        )

    )
}
