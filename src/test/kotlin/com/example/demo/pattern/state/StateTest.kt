package com.example.demo.pattern.state

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StateTest {
    @Test
    fun test() {

    }
}

interface WhatCanHappen {
    fun seeHero()
    fun getHit(pointsOfDamage: Int)
    fun calmAgain()
}

class Snail {
    internal var mood: Mood = Still(this)
    // As before
    private var healthPoints = 10

}

sealed class Mood: WhatCanHappen
class Still(private val snail: Snail) : Mood() {
    override fun seeHero() {
        snail.mood = Aggressive
    }

    override fun getHit(pointsOfDamage: Int) {
        // Same logic from before
    }

    override fun calmAgain() {
        // Return to Still state
    }
}
object Aggressive : Mood() {
    override fun seeHero() {
        TODO("Not yet implemented")
    }

    override fun getHit(pointsOfDamage: Int) {
        TODO("Not yet implemented")
    }

    override fun calmAgain() {
        TODO("Not yet implemented")
    }
}