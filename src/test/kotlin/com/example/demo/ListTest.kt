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
        narrate("Alex (VIP) enters the tavern")
        patrons.add(0, "Alex")
        println(patrons)

        narrate("Brown and Tommy enters the tavern")
        patrons.addAll(listOf("Brown", "Tommy"))
        println(patrons)

        narrate("Eli and Mordoc leaves the tavern")
        patrons.removeAll(listOf("Eli", "Mordoc"))
        println(patrons)

        patrons.forEach { it -> println("Good evening $it") }
        patrons.forEachIndexed {
            index, it -> println("Good evening, $it - you are #${index + 1}")
        }

        val (first, second, third) = listOf<String>("ceo", "cpo", "vp")
        println(first)
        println(second)
        println(third)

        val (one, two, three) = "one,two,three".split(',')
        println(one)
        println(two)
        println(three)
    }

    var narrationModifier: (String) -> String = {
        it
    }

    fun narrate(message: String) {
        println(narrationModifier(message))
    }
}