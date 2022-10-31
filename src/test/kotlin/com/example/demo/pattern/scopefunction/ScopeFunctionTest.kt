package com.example.demo.pattern.scopefunction

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ScopeFunctionTest {
    @Test
    fun test() {
        val clintEastwoodQuotes = mapOf(
            "The Good, The Bad, The Ugly" to "Every gun makes its own tune.",
            "A Fistful Of Dollars" to "My mistake: four coffins."
        )
        val quote = clintEastwoodQuotes["Unforgiven"]
        if (quote != null) {
            println(quote)
        }
    }
}