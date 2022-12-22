package com.example.demo.`guide-kt`

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EnumTest {
    @Test
    fun test() {
        val day = WeekDay.TUE
        println(day.isNotWorkDay())
        println(WeekDay.SUN.isNotWorkDay())

        val blue = RainbowColor.BLUE
        println(blue.isCold)
    }
}

enum class WeekDay {
    MON, TUE, WED, THU, FRI, SAT, SUN;
    val lowerCaseName get() = name.lowercase()
    fun isNotWorkDay() = this in listOf(SAT, SUN)
}

enum class RainbowColor(val isCold: Boolean) {
    RED(false),
    ORANGE(false),
    YELLOW(false),
    GREEN(true),
    BLUE(true),
    INDIGO(true),
    VIOLET(true);

    val isWarm get() = !isCold
}