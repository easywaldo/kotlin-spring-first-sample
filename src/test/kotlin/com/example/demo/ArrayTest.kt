package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArrayTest {
    @Test
    fun test() {
        displayPlayerAges(intArrayOf(20, 19, 25, 23, 43, 32, 39, 23))
    }

    fun displayPlayerAges(playerAges: IntArray) {
        playerAges.forEach {
            println(it)
        }
    }
}