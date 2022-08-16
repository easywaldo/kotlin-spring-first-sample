package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClassTest {
    @Test
    fun player_test() {
        val player = Player()
        player.name = "easy waldo"
        narrate("${player.name}, ${createTitle(player.name)}, heads to the town square")
        player.castFireball()

        println(player.name)
    }
}

class Player {
    var name = "waldo"
        get() = field.replaceFirstChar {
            it.uppercase() }
        set(value) {
            field = value.trim().replace(" ", "")
        }
    fun castFireball(numFireballs: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireballs)")
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