package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ScopeTest {
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

    @Test
    fun let_test() {
        val patrons: List<String> = listOf("waldo", "john", "nick")
        val greeting = patrons.first().let {
            "$it walks over to Madrigal and says, \"Hi! I'm $it. Welcome to Kronstadt!\""
        }
        println(greeting)
    }

    @Test
    fun let_test_or_null() {
        val patrons: List<String> = listOf()
        val greeting = patrons.firstOrNull()?.let {
            "$it walks over to Madrigal and says, \"Hi! I'm $it. Welcome to Kronstadt!\""
        } ?: "Nobody greets Madrigal because the tavern is empty"
        println(greeting)
    }
}