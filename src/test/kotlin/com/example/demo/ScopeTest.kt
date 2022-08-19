package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

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
    fun let_test_3() {
        val hiddenQuest: String = "kotlin and python"
        val questMessage: String = hiddenQuest?.let {
            """
                You should master $hiddenQuest !!
                And make web service using related framework !!
            """.trimMargin()
        }
        println(questMessage)
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

    @Test
    fun with_test() {
        val nameTooLong: Boolean = with("Polarcubis, Supreme Master of NyeHack") {
            length >= 20
        }
        println(nameTooLong)
    }

    @Test
    fun also_test() {
        var fileContents: List<String>
        File("test.txt")
            .also {
                println(it.name)
            }
            .readLines()
            .also {
                fileContents = it
            }
        println("done")
        fileContents.forEach {
            println(it)
        }
    }

    @Test
    fun take_if_test() {
        val fileContents = File("test.txt")
            .takeIf { it.exists() }
            ?.readText()
        println(fileContents)

        val noneContents = File("none.txt")
            .takeIf { it.exists() }
            ?.readText()
        println("none contents : $noneContents")
    }

    @Test
    fun filter_also_test_2() {
        var goodCompany: List<Company> = mutableListOf()
        val companies = listOf<Company>(
            Company(name = "alpha", level = 1, amount = 1000),
            Company(name = "bravo", level = 2, amount = 1500),
            Company(name = "delta", level = 1, amount = 1100),
            Company(name = "charly", level = 2, amount = 1800)
        )
        companies.filter {
            c -> c.amount > 1000
        }.also { c ->
            goodCompany += c
        }

        goodCompany.forEach {
            println(it.name)
        }
    }
}

data class Employee(val name: String, val language: String)
data class Company(val name: String, val level: Int, val amount: Int)