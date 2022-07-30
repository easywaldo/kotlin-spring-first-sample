package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArrayTest {
    @Test
    fun test() {
        displayPlayerAges(intArrayOf(20, 19, 25, 23, 43, 32, 39, 23))
        var x = listOf(mutableListOf(1,2,3))
        var y = listOf(mutableListOf(1,2,3))

        assert(x == y)
        x[0].add(3)

        assert(x == y)
    }

    fun displayPlayerAges(playerAges: IntArray) {
        playerAges.forEach {
            println(it)
        }
    }
}