package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MapTest {
    @Test
    fun test() {
        visitTavern()
    }

    private val TAVERN_MASTER = "Tenary Glows"
    private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
    private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

    val heroName: String = "easywaldo"

    fun visitTavern() {
        val patrons: MutableSet<String> = mutableSetOf()
//        val patronGold = mapOf(
//            TAVERN_MASTER to 98.21,
//            heroName to 4.50
//        )

//        val patronGold = mapOf(
//            Pair("Madrigal", 4.50),
//            Pair("Bristone", 2.19)
//        )

        val patronGold = mutableMapOf(
            "Bob" to 2.1,
            "Nix" to 3.1,
            "Nix" to 4.2
        )
        while (patrons.size < 10) {
            patrons += "${firstNames.random()} ${lastNames.random()}"
        }
        println(patronGold)
        narrate("$heroName sees several patrons in the tavern:")

        val goldPoint = patronGold.getOrDefault("Leo", 3.14)
        println(goldPoint)

        while (patrons.size < 10) {
            val patronName = "${firstNames.random()} ${lastNames.random()}"
            patrons += patronName
            patronGold += patronName to 6.0
        }

        narrate("$heroName sees several patrons in the tavern:")
        narrate(patrons.joinToString())
        println("patronSize : ${patronGold.size}")
        patronGold.forEach {
            println("patronGold info ====================")
            println("${it.key} : ${it.value}")
        }

        val testMap = mutableMapOf(
            "alpha" to 3,
            "bravo" to 2,
            "charly" to 8,
            "delta" to 1
        )
        testMap += mapOf(
            "Eli" to 7,
            "Mordoc" to 1,
            "Phizo" to 9
        )
        testMap.forEach {
            it -> println("${it.key} : ${it.value}")
        }

        testMap.put("Eli", 100)
        testMap.putAll(listOf(
            "Phizo" to 1000,
            "Tada" to 999
        ))
        testMap.forEach {
            it -> println("${it.key} : ${it.value}")
        }
        testMap.forEach { (key, value) -> println("${key} : ${value}") }

    }

    var narrationModifier: (String) -> String = {
        it
    }

    fun narrate(message: String) {
        println(narrationModifier(message))
    }
}