package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PairTest {

    @Test
    fun test() {
        val plus = plus(Tuple(1, 3))
        println(plus)
    }
}

data class Tuple(val a: Int, val b: Int)

fun plus(tuple: Tuple) = tuple.a + tuple.b
