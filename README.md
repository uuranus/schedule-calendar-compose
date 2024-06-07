# Schedule Calendar



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

## Usage
1. 데이터 쓰는 법
![기본값 스크린샷](https://github.com/uuranus/schedule-calendar-compose/assets/72340294/23c7b74e-4834-49be-a9cb-fd9f3cc1c554)
![일요일 먼저](https://github.com/uuranus/schedule-calendar-compose/assets/72340294/6e373d5f-f8bb-4e5a-b28f-b9f90d7e3a99)

![주말 색깔 설정](https://github.com/uuranus/schedule-calendar-compose/assets/72340294/43519c0d-64e4-4c12-9d79-6dd2f2cf2bb8)
![다크모드](https://github.com/uuranus/schedule-calendar-compose/assets/72340294/94d61c14-49bc-47be-abb7-fca962a54231)
![Group 50](https://github.com/uuranus/schedule-calendar-compose/assets/72340294/b32608ae-bf7c-40aa-b63c-8cf26134ec6b)
