package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FunctorsTest {
    @Test
    fun test() {
        listOf<Int>(1, 2, 3)
            .map { number -> number * 2 }
            .map(Int::toString)
            .forEach(::println)
    }
}