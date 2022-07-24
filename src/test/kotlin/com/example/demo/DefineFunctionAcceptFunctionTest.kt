package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DefineFunctionAcceptFunctionTest {
    var narrationModifier: (String) -> String = { it }

    fun narrate(message: String, modifier: (String) -> String = { narrationModifier(it)}) {
        println(modifier(message))
    }

    @Test
    fun test() {
        narrate("A hero enters the town of Kronstadt. What is their name?", {
            message -> "\u001b[33;1m$message\u001b[0m"
        })
        val heroName = readLine() ?: ""
        narrate("$heroName heads to the town square")
    }
}