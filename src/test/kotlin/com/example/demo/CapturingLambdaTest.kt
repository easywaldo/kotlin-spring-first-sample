package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random
import kotlin.random.nextInt

@SpringBootTest
class CapturingLambdaTest {
    var narrationModifier: (String) -> String = {
        it
    }

    fun narrate(message: String) {
        println(narrationModifier(message))
    }

    @Test
    fun test() {
        changeNarratorMood()
    }

    fun changeNarratorMood() {
        val mood: String
        var modifier: (String) -> String = {it}

        when (Random.nextInt(1..4)) {
            1 -> {
                mood = "loud"
                modifier = {
                        message ->
                    val numExclamationPoints = 3
                    message.uppercase() + "!".repeat(numExclamationPoints)
                }
            }
            2 -> {
                mood = "tired"
                modifier = {
                        message ->
                    message.lowercase().replace(" ", "...")
                }
            }
            3 -> {
                mood = "unsure"
                modifier = {
                        message -> "$message?"
                }
            }
            4 -> {
                var narrationsGiven = 0
                mood = "like sending an itemized bill"
                modifier = { message ->
                    narrationsGiven++
                    "$message.\n(I have narrated $narrationsGiven things)"
                }
            }
            else -> {
                mood = "professional"
                modifier = {
                        message -> "$message."
                }
            }
        }

        narrationModifier = modifier
        narrate("The narrator begins to feel $mood")
    }
}