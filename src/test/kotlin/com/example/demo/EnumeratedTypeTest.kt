package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EnumeratedTypeTest {
    @Test
    fun test() {
        println(Direction.East)
    }
}

enum class Direction {
    North,
    East,
    South,
    West,
}
