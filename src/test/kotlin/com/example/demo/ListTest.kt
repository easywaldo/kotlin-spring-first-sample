package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ListTest {
    @Test
    fun test() {
        val patrons = listOf("Alpha", "Bravo", "Delta", 1)
        println(patrons)

        println(patrons.first())
        println(patrons.last())

        println(patrons.getOrElse(4) { "Unknown Patron" })

        val alphaMessage = if (patrons.contains("Alpha")) {
            "Alpha is in the back playing cards"
        } else {
            "Alpha isn't here"
        }
        println(alphaMessage)

        val otherMessage = if (patrons.containsAll(listOf("Alpha", "Bravo"))) {
            "Good list"
        } else {
            "None list"
        }
        println(otherMessage)
    }

    @Test
    fun visitTavern() {
        var patrons = mutableListOf("Eli", "Mordoc", "Sophie")
        narrate("Eli leaves the tavern")
        patrons.remove("Eli")
        narrate("Alex enters the tavern")
        patrons.add("Alex")
        println(patrons)
    }

    var narrationModifier: (String) -> String = {
        it
    }

    fun narrate(message: String) {
        println(narrationModifier(message))
    }
}