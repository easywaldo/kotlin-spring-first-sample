package com.example.demo.interoperability

import Jhava
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Hero {
    @Test
    fun test() {
        val adversary = Jhava()
        println(adversary.utterGreeting())

        val friendshipLevel = adversary.determineFriendshipLevel()
        println(friendshipLevel?.lowercase() ?: "It's complicated")

        val adversaryHitPoints: Int = adversary.hitPoints
        println(adversaryHitPoints.coerceAtLeast(100))
        println(adversaryHitPoints.javaClass)

        adversary.greeting = "Hello, Hero."
        println(adversary.utterGreeting())


    }
}