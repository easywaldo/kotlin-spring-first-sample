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

    @Test
    fun let_test_2() {
        val patrons: List<Employee> = listOf(
            Employee(name = "waldo", language = "kotlin"),
            Employee(name = "john", language = "python"),
        )
        val greeting = patrons.last().let {
            "hello ${it.name} you are ${it.language} developer!!"
        }
        println(greeting)
    }

    @Test
    fun run_test() {
        val tavernPlaylist = mutableListOf<String>("waldo", "john", "horn")
        val nowPlayingMessage: String = tavernPlaylist.run {
            shuffle()
            "${first()} is currently playing in the tavern"
        }
        println(nowPlayingMessage)
    }

    @Test
    fun run_test_2() {
        val healthPoints = 90
        val healthStatus = run {
            if (healthPoints == 100) {
                "perfect health"
            } else {
                "has injuries"
            }
        }
        println(healthStatus)
    }
}

data class Employee(val name: String, val language: String)