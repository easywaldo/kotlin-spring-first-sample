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

enum class Direction(private val directionCoordinate: Coordinate) {
    North(Coordinate(0, -1)),
    East(Coordinate(1, 0)),
    South(Coordinate(0, -1)),
    West(Coordinate(-1, 0)),
}
