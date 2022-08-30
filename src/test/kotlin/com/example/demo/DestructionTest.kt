package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DestructionTest {
    @Test
    fun test() {
        val (experience, level) = PlayerScore(1250, 5)
        println("exp: $experience, level: $level")
    }
}

class PlayerScore(val experience: Int, val level: Int) {
    operator fun component1() = experience
    operator fun component2() = level
}