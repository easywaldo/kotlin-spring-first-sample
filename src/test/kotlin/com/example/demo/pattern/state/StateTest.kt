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

class Snail : WhatCanHappen {
    private var mood: Mood = Still
    // As before
    private var healthPoints = 10
    override fun seeHero() {
        mood = when(mood) {
            is Still -> Aggressive
            else -> mood
        }
    }
    override fun getHit(pointsOfDamage: Int) {
        healthPoints -= pointsOfDamage
        mood = when {
            (healthPoints <= 0) -> Dead
            mood is Aggressive -> Retreating
            else -> mood
        }
    }
    override fun calmAgain() {
    }
}

sealed class Mood {
    // Some abstract methods here, like draw(), for example
}
object Still : Mood()
object Aggressive : Mood()
object Retreating : Mood()
object Dead : Mood()