# Schedule Calendar
![schedule-calendar](https://github.com/uuranus/schedule-calendar-compose/assets/72340294/87a51698-fbd8-41a7-a425-5bb67952b01f)

# Download
1. Add it in your root build.gradle
``` kotlin
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

2. Add the dependency
``` kotlin
dependencies {
	        implementation 'com.github.uuranus:schedule-calendar-compose:latest_version'
	}
```

# Usage

## Adding Data
``` kotlin
ScheduleCalendar(
        schedules = mapOf(
            ScheduleDate.create(2024, 6, 1) to ScheduleInfo(
                isCheckNeeded = false,
                schedules = listOf(
                    ScheduleData(
                        title = "schedule1",
                        color = Purple,
                        detail = "Schedule Info 1",
                    )
                )
            ),
        )
    )
```

- The data structure looks like this.
- You can set a class that contains more detailed schedule information through generics.

<img src="https://github.com/uuranus/schedule-calendar-compose/assets/72340294/1652fccd-2417-4d3f-8b55-6d6e8d093f63" alt="스케줄 데이터 구조" width="600" />

## Customizing the Date

### Default
-  Starts on Monday, with no distinction in color for weekends.
<img src="https://github.com/uuranus/schedule-calendar-compose/assets/72340294/caa063c8-f772-42df-8b4b-77f4a302ab0a" alt="기본값 스크린샷" width="300" />

---
### From Sunday
``` kotlin
 ScheduleCalendar(
        schedules = schedules,
        isMondayFirst = false
    )
```
<img src="https://github.com/uuranus/schedule-calendar-compose/assets/72340294/144fa044-52f8-4bc9-887e-4f36f29e92c4" alt="일요일 first" width="300" />

---
### Weekend Coloring
``` kotlin
 ScheduleCalendar(
        schedules = schedules,
        calendarColors = ScheduleCalendarDefaults.colors(
            lightColors = ScheduleCalendarDefaults.defaultLightColors().copy(
                saturdayColor = Blue,
                sundayColor = Red
            ),
            darkColors = ScheduleCalendarDefaults.defaultDarkColors().copy(
                saturdayColor = DarkBlue,
                sundayColor = DarkRed
            ),
        )
    )
```
<img src="https://github.com/uuranus/schedule-calendar-compose/assets/72340294/8bb26444-9ec5-4a7d-812e-7a62dc0a420a" alt="주말 색깔 first" width="300" />


---
### DarkMode
- The color of the schedule is set to the Light version and automatically changes to Dark mode.
<img src="https://github.com/uuranus/schedule-calendar-compose/assets/72340294/9b2d03bf-6906-404b-b3b8-7355cedf7147" alt="다크 모드 first" width="300" />

---
### YearMonth Formatting
- the names of the days and months to reflect the system's language and you can modify the format of the year and month.
``` kotlin
ScheduleCalendar(
        schedules = schedules,
        calendarFormat = ScheduleCalendarDefaults.formats().copy(
            monthHeaderFormat = "MMMM YYYY"
        )
    )
```
<img src="https://github.com/uuranus/schedule-calendar-compose/assets/72340294/0586ee5a-5ff9-419b-a98d-e8cd23f2f190" alt="시스템 언어 설정" width="300" />


# License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.
