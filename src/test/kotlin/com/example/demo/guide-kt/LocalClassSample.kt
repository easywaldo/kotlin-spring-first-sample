package com.example.demo.`guide-kt`

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LocalClassSample {
    @Test
    fun test() {
        var guardRail = 100
        class Point(val x: Int, val y: Int) {
            fun shift(dx: Int, dy: Int): Point = Point(x + dx, y + dy)
            override fun toString(): String {
                return "($x, $y)"
            }
            fun setGuardRail(point: Int) {
                guardRail += point
            }
        }
        val p1 = Point(10, 10)
        println(p1.shift(-1, 10))
        p1.setGuardRail(19)
        println(guardRail)
    }
}