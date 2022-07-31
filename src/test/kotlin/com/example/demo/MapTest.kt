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
        val patronGold = mapOf(
            TAVERN_MASTER to 98.21,
            heroName to 4.50
        )
        while (patrons.size < 10) {
            patrons += "${firstNames.random()} ${lastNames.random()}"
        }
        println(patronGold)
        narrate("$heroName sees several patrons in the tavern:")

    }

    var narrationModifier: (String) -> String = {
        it
    }

    fun narrate(message: String) {
        println(narrationModifier(message))
    }
}