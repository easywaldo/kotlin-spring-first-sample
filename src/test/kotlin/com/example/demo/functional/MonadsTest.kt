package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MonadsTest {
    @Test
    fun test() {
        val result = listOf<Int>(1,2,3)
            .flatMap { i -> listOf(i * 2, i + 3) }
            .joinToString()
        println(result)
    }
}