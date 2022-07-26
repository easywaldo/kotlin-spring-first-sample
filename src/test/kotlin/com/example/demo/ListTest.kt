package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ListTest {
    @Test
    fun test() {
        val patrons = listOf("Alpha", "Bravo", "Delta", 1)
        println(patrons)

        println(patrons.first())
        println(patrons.last())
    }
}