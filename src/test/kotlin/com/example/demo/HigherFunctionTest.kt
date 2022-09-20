package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HigherFunctionTest {
    @Test
    fun test() {
        var total: Int = 0
        println(measureTime({
            sumTest()
            sumTest()
        }))
    }

    fun measureTime(action: ()->Unit): Long {
        val start = System.nanoTime()

        action?.invoke()

        return System.nanoTime() - start
    }

    fun sumTest() {
        var total: Int = 0
        for (i in 1 .. 10000) {
            total += 10
        }
    }
}