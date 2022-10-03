package com.example.demo.pattern.state

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StateTest {
    @Test
    fun test() {
        val still = Still(Snail())
        still.seeHero()
        still.getHit(3)
        still.calmAgain()
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
    var healthPoints: Int = 10
        get() = field
        set(value) {
            field = value
        }


}

sealed class Mood: WhatCanHappen
class Still(private val snail: Snail) : Mood() {
    override fun seeHero() {
        snail.mood = Aggressive
        println("see hero aggressive")
    }

    override fun getHit(pointsOfDamage: Int) {
        snail.healthPoints -= pointsOfDamage
    }

    override fun calmAgain() {
        // Return to Still state
        println(snail.healthPoints)
    }
}
object Aggressive : Mood() {
    override fun seeHero() {
        println("see hero aggressive")
    }

    override fun getHit(pointsOfDamage: Int) {
        TODO("Not yet implemented")
    }

    override fun calmAgain() {
        TODO("Not yet implemented")
    }
}