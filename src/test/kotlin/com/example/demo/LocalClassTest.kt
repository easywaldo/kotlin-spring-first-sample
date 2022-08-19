package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LocalClassTest {
    @Test
    fun test() {
        class Point(val x: Int, val y: Int) {
            fun shift(dx: Int, dy: Int): Point = Point(x + dx, y + dy)
            override fun toString(): String {
                return "($x, $y)"
            }
        }

        val p = Point(10, 10)
        println(p.shift(5, 5))
    }
}