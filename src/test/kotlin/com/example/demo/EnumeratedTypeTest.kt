package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EnumeratedTypeTest {
    @Test
    fun test() {
        println(Direction.East)

        var currentPosition = Coordinate(5, 2)
        currentPosition = Direction.East.updateCoordinate(currentPosition)
        println(currentPosition)
    }
}

enum class Direction(private val directionCoordinate: Coordinate) {
    North(Coordinate(0, -1)),
    East(Coordinate(1, 0)),
    South(Coordinate(0, -1)),
    West(Coordinate(-1, 0));

    fun updateCoordinate(coordinate: Coordinate) =
        Coordinate(
            x = coordinate.x + directionCoordinate.x,
            y = coordinate.y + directionCoordinate.y)
}
