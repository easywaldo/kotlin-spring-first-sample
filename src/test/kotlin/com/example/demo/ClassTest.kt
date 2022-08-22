package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClassTest {
    @Test
    fun player_test() {
        val player = Player("easywaldo", "Seoul", 100, true)
        narrate("${player.name}, ${createTitle(player.name)}, heads to the town square")
        player.castFireball()

        player.changeName("easy waldo")
        println(player.name)

        player.changeName("1292345")
        println(player.title)
    }
}

class Player(
    initialName: String,
    val hometown: String = "Neversummer",
    var healthPoints: Int,
    val isImmortal: Boolean) {
    var name = "waldo"
        get() = field.replaceFirstChar {
            it.uppercase() }
        private set(value) {
            field = value.trim().replace(" ", "")
        }
    val title: String
        get() = when {
            name.all {
                it.isDigit()
            } -> "The Identifiable"
            name.none() {
                it.isLetter()
            } -> "The Witness Protection Member"
            name.count() {
                it.lowercase() in "aeiou"
            } > 4 -> "The Master of Vowels"
            else -> "The Renowned Hero"
        }

    fun castFireball(numFireballs: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireballs)")
    }
    fun changeName(newName: String) {
        narrate("$name legally changes their name to $newName")
        name = newName
    }
    val prophecy by lazy {
        narrate("$name embarks on an arduous quest to locate a fortune teller")
        Thread.sleep(3000)
        narrate("The fortune teller bestows a prophecy upon $name")

        "An intrepid hero from $hometown shall some day " + listOf(
            "form an unlikely bond between two warring factions",
            "take possession of an otherworldly blade",
            "bring the gift of creation back to the world",
            "best the world-eater"
        ).random()
    }
    fun prophesize() {
        narrate("$name thinks about their future")
        narrate("A fortune teller told Madrigal, \"$prophecy\"")
    }
}

var narrationModifier: (String) -> String = {
    it
}

fun narrate(message: String) {
    println(narrationModifier(message))
}

fun createTitle(name: String): String {
    return when {
        name.count {
            it.lowercase() in "aeiou"
        } > 4 -> "The Master of Vowels"
        name.none {
            it.isLetter()
        } -> "The Witness Protection"
        name.all {
            it.isDigit()
        } -> "The Identifiable"
        else -> "The Renowned Hero"
    }
}