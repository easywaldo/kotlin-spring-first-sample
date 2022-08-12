package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplyTest {
    @Test
    fun apply_test() {
        val (isAfterMidnight, isOpenMicNight, isHappyHour) = listOf(true, false, true)
        val guestList: List<String> = mutableListOf<String>().apply {
            if (isAfterMidnight) { add("Sidney") }
            if (isOpenMicNight) { add("Janet") }
            if (isHappyHour) { add("Jamie") }
            if (contains("Janet") || contains("Jamie")) { add("Hal") }
        }.toList()
        guestList.forEach {
            println(it)
        }
    }
}