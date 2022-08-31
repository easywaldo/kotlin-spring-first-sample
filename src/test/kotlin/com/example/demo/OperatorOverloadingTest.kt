package com.example.demo

import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test

@SpringBootTest
class OperatorOverloadingTest {
    @Test
    fun test() {
        var originCoordinate = Coordinate(x = 100, y = 100)
        var movementCoordinate = Coordinate(x = 5, y = 10)
        var result = originCoordinate + movementCoordinate
        println(result)
    }
}