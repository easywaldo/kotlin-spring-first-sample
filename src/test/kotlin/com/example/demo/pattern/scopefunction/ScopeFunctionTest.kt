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

        clintEastwoodQuotes["Unforgiven"]?.let {
            println(it)
        }

        clintEastwoodQuotes["Unforgiven"].let {
            println(it) // null printing
        }
    }

    @Test
    fun apply_test() {
        val agent = JamesBond()
        agent.name = "Sean Connery"
        agent.movie = "Dr. No"

        val `007`= JamesBond().apply {
            this.name = "Sean Connery"
            this.movie = "Dr. No"
        }
        println(`007`)

        val sevenAgent = JamesBond().apply {
            name = "easywaldo"
            movie = "my movie"
        }
        println(sevenAgent.name)
    }

    class JamesBond {
        lateinit var name: String
        lateinit var movie: String
        lateinit var alsoStarring: String
    }
}