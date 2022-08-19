package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LateInitTest {
    @Test
    fun test() {
        val arena = Arena()
        arena.prepareArena()
        println(arena.opponentName)

        arena.prophesize()
        println("time is going....")
        println("time is going....")
        arena.prophesize()
    }
}

class Arena {
    var isArenaOpen = false
    lateinit var opponentName: String

    fun prepareArena() {
        isArenaOpen = true
        opponentName = getWillingCombatants().random()
    }

    private fun getWillingCombatants() = listOf<String>("Cornelius", "Cheryl", "Ralph", "Deborah")

    val prophecy by lazy {
        Thread.sleep(3000)
        "An intrepid hero from hometown shall some day " + listOf(
            "form an unlikely bond between two warring factions",
            "take possession of an otherworldly blade",
            "bring the gift of creation back to the world",
            "best the world-eater"
        ).random()
    }

    fun prophesize() {
        println("A fortune teller told Madrigal, $prophecy")
    }
}