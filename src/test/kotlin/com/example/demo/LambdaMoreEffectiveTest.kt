package com.example.demo

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random
import kotlin.random.nextInt

@SpringBootTest
class LambdaMoreEffectiveTest {
    var narrationModifier: (String) -> String = {
        it
    }

    fun narrate(message: String) {
        println(narrationModifier(message))
    }

    fun changeNarratorMood() {
        val mood: String
        val modifier: (String) -> String

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

    @RepeatedTest(value = 8)
    fun test() {
        changeNarratorMood()
    }

    @Test
    fun test2() {
        narrate("A hero enters the town of Kronstadt. What is their name?")
        val heroName = readLine() ?: ""

        changeNarratorMood()
        narrate("$heroName heads to the town square")
    }
}