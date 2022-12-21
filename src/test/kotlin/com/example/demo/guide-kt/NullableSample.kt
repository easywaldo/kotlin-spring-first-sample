package com.example.demo.`guide-kt`

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NullableSample {
    @Test
    fun test() {
        val number = 90
        println(describeNumber(number))
        println(describeNumber(null))
    }

    fun describeNumber(n: Int?) = when(n) {
        null -> "null"
        in 0..10 -> "small"
        in 11..100 -> "large"
        else -> "out of range"
    }
}